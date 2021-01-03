@file:JvmName("ReadableSourceExtensions")

package org.jire.kna

fun ReadableSource.byte(address: Long, offset: Long = 0): Byte {
	val readPointer = readPointer(address, 1)
	if (!readPointer.readable()) return 0
	return readPointer.getByte(offset)
}

fun ReadableSource.short(address: Long, offset: Long = 0): Short {
	val readPointer = readPointer(address, 2)
	if (!readPointer.readable()) return 0
	return readPointer.getShort(offset)
}

fun ReadableSource.int(address: Long, offset: Long = 0): Int {
	val readPointer = readPointer(address, 4)
	if (!readPointer.readable()) return 0
	return readPointer.getInt(offset)
}

fun ReadableSource.long(address: Long, offset: Long = 0): Long {
	val readPointer = readPointer(address, 8)
	if (!readPointer.readable()) return 0
	return readPointer.getLong(offset)
}

fun ReadableSource.float(address: Long, offset: Long = 0): Float {
	val readPointer = readPointer(address, 4)
	if (!readPointer.readable()) return 0F
	return readPointer.getFloat(offset)
}

fun ReadableSource.double(address: Long, offset: Long = 0): Double {
	val readPointer = readPointer(address, 8)
	if (!readPointer.readable()) return 0.0
	return readPointer.getDouble(offset)
}

fun ReadableSource.char(address: Long, offset: Long = 0) = short(address, offset).toChar()

fun ReadableSource.boolean(address: Long, offset: Long = 0) =
	byte(address, offset).toInt() != 0

inline operator fun <reified T : Any> ReadableSource.get(address: Long, offset: Long = 0) = when (T::class) {
	Byte::class -> byte(address, offset)
	Short::class -> short(address, offset)
	Int::class -> int(address, offset)
	Long::class -> long(address, offset)
	Float::class -> float(address, offset)
	Double::class -> double(address, offset)
	Char::class -> char(address, offset)
	Boolean::class -> boolean(address, offset)
	else -> throw IllegalArgumentException()
}