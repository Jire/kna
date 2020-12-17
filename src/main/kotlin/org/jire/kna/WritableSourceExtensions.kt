@file:JvmName("WritableSourceExtensions")

package org.jire.kna

import com.sun.jna.Pointer

inline fun WritableSource.write(address: Long, bytes: Long, writeBody: Pointer.() -> Unit) =
	write(address, MemoryCache[bytes].apply(writeBody))

operator fun WritableSource.set(address: Long, value: Byte) = write(address, Byte.SIZE_BYTES.toLong()) {
	setByte(0, value)
}

operator fun WritableSource.set(address: Long, value: Short) = write(address, Short.SIZE_BYTES.toLong()) {
	setShort(0, value)
}

operator fun WritableSource.set(address: Long, value: Int) = write(address, Int.SIZE_BYTES.toLong()) {
	setInt(0, value)
}

operator fun WritableSource.set(address: Long, value: Long) = write(address, Long.SIZE_BYTES.toLong()) {
	setLong(0, value)
}

operator fun WritableSource.set(address: Long, value: Float) = write(address, Float.SIZE_BYTES.toLong()) {
	setFloat(0, value)
}

operator fun WritableSource.set(address: Long, value: Double) = write(address, Double.SIZE_BYTES.toLong()) {
	setDouble(0, value)
}

operator fun WritableSource.set(address: Long, value: Char) = write(address, Char.SIZE_BYTES.toLong()) {
	setChar(0, value)
}

operator fun WritableSource.set(address: Long, value: Boolean) = set(address, (if (value) 1 else 0).toByte())