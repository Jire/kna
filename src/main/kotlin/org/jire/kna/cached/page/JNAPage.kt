package org.jire.kna.cached.page

import com.sun.jna.Memory
import com.sun.jna.Pointer

internal class JNAPage(
	override val source: PageCachedReadableSource,
	override val size: Long,
	override val cacheExpireMillis: Long
) : Page {
	
	val memory = Memory(size)
	val originalPeer = Pointer.nativeValue(memory)
	
	fun setPeer(offset: Long) = Pointer.nativeValue(memory, originalPeer + offset)
	fun resetPeer() = Pointer.nativeValue(memory, originalPeer)
	
	@Volatile
	var lastRead = UNSET_LAST_READ
	
	override fun needsRead(now: Long) = lastRead == UNSET_LAST_READ || now - lastRead >= cacheExpireMillis
	
	override fun read(address: Long) = source.read(address, memory, size).apply {
		if (this) lastRead = System.currentTimeMillis()
	}
	
	companion object {
		const val UNSET_LAST_READ = -1L
	}
	
}