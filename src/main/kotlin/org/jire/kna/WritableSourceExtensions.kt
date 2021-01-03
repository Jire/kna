@file:JvmName("WritableSourceExtensions")

package org.jire.kna

import net.openhft.chronicle.core.OS

operator fun WritableSource.set(address: Long, value: Byte) {
	val writePointer = PointerCache[1]
	OS.memory().writeByte(writePointer.address, value)
	write(address, writePointer, 1)
}

operator fun WritableSource.set(address: Long, value: Short) {
	val writePointer = PointerCache[2]
	OS.memory().writeShort(writePointer.address, value)
	write(address, writePointer, 2)
}

operator fun WritableSource.set(address: Long, value: Int) {
	val writePointer = PointerCache[4]
	OS.memory().writeInt(writePointer.address, value)
	write(address, writePointer, 4)
}

operator fun WritableSource.set(address: Long, value: Long) {
	val writePointer = PointerCache[8]
	OS.memory().writeLong(writePointer.address, value)
	write(address, writePointer, 8)
}

operator fun WritableSource.set(address: Long, value: Float) {
	val writePointer = PointerCache[4]
	OS.memory().writeFloat(writePointer.address, value)
	write(address, writePointer, 4)
}

operator fun WritableSource.set(address: Long, value: Double) {
	val writePointer = PointerCache[8]
	OS.memory().writeDouble(writePointer.address, value)
	write(address, writePointer, 8)
}

operator fun WritableSource.set(address: Long, value: Char) = set(address, value.toShort())

operator fun WritableSource.set(address: Long, value: Boolean) = set(address, (if (value) 1 else 0).toByte())