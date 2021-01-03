package org.jire.kna.cached.page

import com.sun.jna.Memory
import it.unimi.dsi.fastutil.longs.Long2ObjectMap
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
import net.openhft.chronicle.core.OS
import org.jire.kna.cached.CachedReadableSource

interface PageCachedReadableSource : CachedReadableSource {
	
	private companion object {
		val cachedPages: ThreadLocal<Long2ObjectMap<JNAPage>> =
			ThreadLocal.withInitial { Long2ObjectOpenHashMap() }
		val pageSize = OS.pageSize().toLong()
		val pageSizeTrailingZeroBits = pageSize.countTrailingZeroBits()
		
		private fun Long.toPageIndex(trailingZeroBits: Int = pageSizeTrailingZeroBits) =
			(this ushr trailingZeroBits) shl trailingZeroBits
	}
	
	override fun read(address: Long, bytesToRead: Long): Memory? {
		if (address == 0L || address > Int.MAX_VALUE) return null
		if (bytesToRead > pageSize) return super.read(address, bytesToRead)
		
		val pageIndex = address.toPageIndex()
		if (pageIndex == 0L) return null
		
		val pageEndIndex = (address + bytesToRead).toPageIndex()
		if (pageEndIndex == 0L) return null
		
		if (pageIndex != pageEndIndex || address < pageIndex) super.read(address, bytesToRead)
		
		val pages = cachedPages.get()
		
		var page = pages[pageIndex]
		if (page == null) {
			page = JNAPage(this, pageSize, cacheExpireMillis)
			pages[pageIndex] = page
		}
		
		if (page.needsRead()) {
			page.resetPeer()
			if (!page.doRead(pageIndex)) return super.read(address, bytesToRead)
		}
		
		page.setPeer(address - pageIndex)
		return page.memory
	}
	
}