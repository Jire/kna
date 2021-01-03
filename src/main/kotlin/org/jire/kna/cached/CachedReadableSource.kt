package org.jire.kna.cached

import org.jire.kna.ConfigKey
import org.jire.kna.DefaultConfigKey
import org.jire.kna.Pointer
import org.jire.kna.ReadableSource

interface CachedReadableSource : ReadableSource {
	
	companion object {
		const val DEFAULT_CACHE_EXPIRATION_MILLIS = 1L
		const val DISABLED_CACHE_EXPIRATION_MILLIS = 0L
		
		val CACHE_EXPIRATION_MILLIS: ConfigKey<Long> = DefaultConfigKey(DEFAULT_CACHE_EXPIRATION_MILLIS)
	}
	
	val cacheExpireMillis get() = DEFAULT_CACHE_EXPIRATION_MILLIS
	
	fun readCached(address: Long, bytesToRead: Long): Pointer
	
	fun readSource(address: Long, bytesToRead: Long) = super.readPointer(address, bytesToRead)
	
	override fun readPointer(address: Long, bytesToRead: Long): Pointer {
		if (cacheExpireMillis == DISABLED_CACHE_EXPIRATION_MILLIS) return readSource(address, bytesToRead)
		return readCached(address, bytesToRead)
	}
	
}