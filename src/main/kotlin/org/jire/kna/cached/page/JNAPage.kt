package org.jire.kna.cached.page

import org.jire.kna.Pointer

internal class JNAPage(
	override val source: PageCachedReadableSource,
	override val size: Long,
	override val cacheExpireMillis: Long
) : Page {
	
	val address = Pointer.alloc(size)
	
	var lastRead = UNSET_LAST_READ
	
	override fun needsRead(now: Long) = lastRead == UNSET_LAST_READ || now - lastRead >= cacheExpireMillis
	
	override fun read(address: Long) = source.read(address, this.address, size).apply {
		if (this) lastRead = System.currentTimeMillis()
	}
	
	companion object {
		const val UNSET_LAST_READ = -1L
	}
	
}