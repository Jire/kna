package org.jire.kna

interface WritableSource : Source {
	
	fun write(address: Long, data: Pointer, bytesToWrite: Long): Boolean
	
}