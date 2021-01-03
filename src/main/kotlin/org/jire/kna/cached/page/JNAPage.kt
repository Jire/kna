package org.jire.kna.cached.page

import com.sun.jna.Memory
import com.sun.jna.Pointer
import org.jire.kna.ReadableSource

internal class JNAPage(
	override val source: ReadableSource,
	override val size: Long,
	override val cacheExpireMillis: Long
) : Page {
	
	val memory = Memory(size)
	val originalPeer = Pointer.nativeValue(memory)
	
	fun setPeer(offset: Long) = Pointer.nativeValue(memory, originalPeer + offset)
	fun resetPeer() = Pointer.nativeValue(memory, originalPeer)
	
	@Volatile
	var lastRead = -1L
	
	override fun needsRead(now: Long) = lastRead == -1L || now - lastRead >= cacheExpireMillis
	
	override fun read(address: Long) = source.read(address, memory, size).apply {
	
	}
	
	fun doRead(address: Long): Boolean {
		val read = source.read(address, memory, size)
		if (read) lastRead = System.currentTimeMillis()
		return read
	}
	
}