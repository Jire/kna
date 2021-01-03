package org.jire.kna.cached

import org.jire.kna.ConfigKey
import org.jire.kna.DefaultConfigKey
import org.jire.kna.ReadableSource

interface CachedReadableSource : ReadableSource {
	
	companion object {
		const val DEFAULT_CACHE_EXPIRATION_MILLIS = 1L
		
		val CACHE_EXPIRATION_MILLIS: ConfigKey<Long> = DefaultConfigKey(DEFAULT_CACHE_EXPIRATION_MILLIS)
	}
	
	val cacheExpireMillis get() = DEFAULT_CACHE_EXPIRATION_MILLIS
	
}