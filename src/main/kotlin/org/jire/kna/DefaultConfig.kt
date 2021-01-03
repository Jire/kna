package org.jire.kna

import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

class DefaultConfig : Config {
	
	protected val keys: Object2ObjectMap<ConfigKey<*>, Any?> = Object2ObjectOpenHashMap()
	
	override fun <T> set(key: ConfigKey<T>, value: T) {
		keys[key] = value
	}
	
	@Suppress("UNCHECKED_CAST")
	override fun <T> get(key: ConfigKey<T>): T = keys.getOrDefault(key, key.default) as T
	
}