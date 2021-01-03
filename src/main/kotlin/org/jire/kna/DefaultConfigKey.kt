package org.jire.kna

data class DefaultConfigKey<T>(override val default: T) : ConfigKey<T>