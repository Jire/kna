package org.jire.kna.attach

import org.jire.kna.cached.CachedReadableSource
import org.jire.kna.cached.page.PageCachedReadableSource

abstract class CachedAttachedProcess(config: AttachConfig) : AbstractAttachedProcess(config),
	PageCachedReadableSource {
	
	override val cacheExpireMillis = config[CachedReadableSource.CACHE_EXPIRATION_MILLIS]
	
}