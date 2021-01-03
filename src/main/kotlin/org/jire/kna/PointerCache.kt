package org.jire.kna

import com.sun.jna.Native
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap

object PointerCache {
	
	const val MAX_BYTES_TO_CACHE = 128L
	
	private val map = ThreadLocal.withInitial { Long2LongOpenHashMap(64) }
	
	operator fun get(size: Long, clear: Boolean = false): Pointer {
		val map = map.get()
		if (map.containsKey(size)) {
			return Pointer(map.get(size))
		}
		val address = Native.malloc(size)
		if (size <= MAX_BYTES_TO_CACHE) map[size] = address
		return Pointer(address)
	}
	
}