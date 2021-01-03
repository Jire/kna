package org.jire.kna.cached

import org.jire.kna.ReadableSource
import org.jire.kna.attach.AttachConfigKey
import org.jire.kna.attach.DefaultAttachConfigKey

interface CachedReadableSource : ReadableSource {
	
	companion object {
		const val DEFAULT_CACHE_EXPIRATION_MILLIS = 1L
		
		val CACHE_EXPIRATION_MILLIS: AttachConfigKey<Long> = DefaultAttachConfigKey(DEFAULT_CACHE_EXPIRATION_MILLIS)
	}
	
	val cacheExpireMillis get() = DEFAULT_CACHE_EXPIRATION_MILLIS
	
}