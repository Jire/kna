package org.jire.kna.cached

import com.sun.jna.Memory
import org.jire.kna.ConfigKey
import org.jire.kna.DefaultConfigKey
import org.jire.kna.ReadableSource

interface CachedReadableSource : ReadableSource {
	
	companion object {
		const val DEFAULT_CACHE_EXPIRATION_MILLIS = 1L
		const val DISABLED_CACHE_EXPIRATION_MILLIS = 0L
		
		val CACHE_EXPIRATION_MILLIS: ConfigKey<Long> = DefaultConfigKey(DEFAULT_CACHE_EXPIRATION_MILLIS)
	}
	
	val cacheExpireMillis get() = DEFAULT_CACHE_EXPIRATION_MILLIS
	
	fun readCached(address: Long, bytesToRead: Long): Memory?
	
	fun readSource(address: Long, bytesToRead: Long) = super.read(address, bytesToRead)
	
	override fun read(address: Long, bytesToRead: Long): Memory? {
		if (address == 0L || address > Int.MAX_VALUE) return null
		if (cacheExpireMillis == DISABLED_CACHE_EXPIRATION_MILLIS) return readSource(address, bytesToRead)
		return readCached(address, bytesToRead)
	}
	
}