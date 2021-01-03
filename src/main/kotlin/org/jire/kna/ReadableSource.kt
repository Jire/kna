package org.jire.kna

interface ReadableSource : Source {
	
	fun read(address: Long, data: Pointer, bytesToRead: Long): Boolean
	fun read(address: Long, bytesToRead: Long) =
		address != 0L && read(offset(address), PointerCache[bytesToRead], bytesToRead)
	
	fun readPointer(address: Long, data: Pointer, bytesToRead: Long): Pointer =
		if (read(offset(address), data, bytesToRead)) data else Pointer.NULL
	
	fun readPointer(address: Long, bytesToRead: Long) = readPointer(address, PointerCache[bytesToRead], bytesToRead)
	
}