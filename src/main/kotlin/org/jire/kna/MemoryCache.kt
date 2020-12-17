package org.jire.kna

import com.sun.jna.Memory
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap

object MemoryCache {
	
	const val MAX_BYTES_TO_CACHE = 128L
	
	private val map = ThreadLocal.withInitial { Long2ObjectOpenHashMap<Memory>(64) }
	
	operator fun get(size: Long, clear: Boolean = false): Memory {
		val map = map.get()
		return map.get(size)?.apply { if (clear) clear() } ?: Memory(size).apply {
			if (size <= MAX_BYTES_TO_CACHE) map[size] = this
		}
	}
	
}