package org.jire.kna

interface Addressed {
	
	val address: Long
	
	fun offset(offset: Long) = address + offset
	
}