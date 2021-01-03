@file:JvmName("WritableSourceExtensions")

package org.jire.kna

inline fun WritableSource.set(address: Long, size: Long, writeToPointer: Pointer.() -> Unit) =
	write(offset(address), PointerCache[size].apply(writeToPointer), size)

operator fun WritableSource.set(address: Long, value: Byte) = set(address, 1) { setByte(0, value) }
operator fun WritableSource.set(address: Long, value: Short) = set(address, 2) { setShort(0, value) }
operator fun WritableSource.set(address: Long, value: Int) = set(address, 4) { setInt(0, value) }
operator fun WritableSource.set(address: Long, value: Long) = set(address, 8) { setLong(0, value) }
operator fun WritableSource.set(address: Long, value: Float) = set(address, 4) { setFloat(0, value) }
operator fun WritableSource.set(address: Long, value: Double) = set(address, 1) { setDouble(0, value) }
operator fun WritableSource.set(address: Long, value: Char) = set(address, value.toShort())
operator fun WritableSource.set(address: Long, value: Boolean) = set(address, (if (value) 1 else 0).toByte())