package org.jire.kna.attach

data class DefaultAttachConfigKey<T>(override val default: T) : AttachConfigKey<T>