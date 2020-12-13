package org.jire.kna

import com.sun.jna.Memory
import com.sun.jna.Pointer

interface WritableSource : Source {
	
	fun write(address: Pointer, data: Pointer, bytesToWrite: Long): Boolean
	
	fun write(address: Long, data: Memory, bytesToWrite: Long = data.size()) =
		write(PointerCache[offset(address)], data, bytesToWrite)
	
}