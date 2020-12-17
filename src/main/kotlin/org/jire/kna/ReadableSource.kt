package org.jire.kna

import com.sun.jna.Memory
import com.sun.jna.Pointer

interface ReadableSource : Source {
	
	fun read(address: Pointer, data: Pointer, bytesToRead: Long): Boolean
	
	fun read(address: Long, data: Memory, bytesToRead: Long = data.size()) =
		read(addressToPointer(address), data, bytesToRead)
	
	fun read(address: Pointer, bytesToRead: Long): Memory? = MemoryCache[bytesToRead].apply {
		read(address, this, bytesToRead)
	}
	
	fun read(address: Long, bytesToRead: Long) = read(addressToPointer(address), bytesToRead)
	
}