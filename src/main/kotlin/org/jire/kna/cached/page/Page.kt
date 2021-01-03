package org.jire.kna.cached.page

import org.jire.kna.Addressed
import org.jire.kna.ReadableSource

interface Page {
	
	val source: ReadableSource
	
	val size: Long
	
	val cacheExpireMillis: Long
	
	fun needsRead(now: Long = System.currentTimeMillis()): Boolean
	
	fun read(address: Long): Boolean
	
	fun read(addressed: Addressed) = read(addressed.address)
	
}