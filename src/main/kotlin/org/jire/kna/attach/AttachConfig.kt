package org.jire.kna.attach

interface AttachConfig {
	
	operator fun <T> set(key: AttachConfigKey<T>, value: T)
	
	operator fun <T> get(key: AttachConfigKey<T>): T
	
}