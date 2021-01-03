package org.jire.kna.attach

import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

class DefaultAttachConfig : AttachConfig {
	
	protected val keys: Object2ObjectMap<AttachConfigKey<*>, Any?> = Object2ObjectOpenHashMap()
	
	override fun <T> set(key: AttachConfigKey<T>, value: T) {
		keys[key] = value
	}
	
	@Suppress("UNCHECKED_CAST")
	override fun <T> get(key: AttachConfigKey<T>): T = keys.getOrDefault(key, key.default) as T
	
}