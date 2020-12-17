package org.jire.kna

interface Source : Addressed {
	
	fun addressToPointer(address: Long) = PointerCache[offset(address)]
	
}