package org.jire.kna.attach

import org.jire.kna.Config
import org.jire.kna.cached.CachedReadableSource
import org.jire.kna.cached.page.PageCachedReadableSource

abstract class CachedAttachedProcess(config: Config) : AbstractAttachedProcess(config),
	PageCachedReadableSource {
	
	override val cacheExpireMillis = config[CachedReadableSource.CACHE_EXPIRATION_MILLIS]
	
}