package org.jire.kna

interface Config {
	
	operator fun <T> set(key: ConfigKey<T>, value: T)
	
	operator fun <T> get(key: ConfigKey<T>): T
	
}