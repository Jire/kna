@file:JvmName("ReadableSourceExtensions")

package org.jire.kna

fun ReadableSource.byte(address: Long, offset: Long = 0) =
	read(address, Byte.SIZE_BYTES.toLong())?.getByte(offset) ?: 0

fun ReadableSource.short(address: Long, offset: Long = 0) =
	read(address, Short.SIZE_BYTES.toLong())?.getShort(offset) ?: 0

fun ReadableSource.int(address: Long, offset: Long = 0) =
	read(address, Int.SIZE_BYTES.toLong())?.getInt(offset) ?: 0

fun ReadableSource.long(address: Long, offset: Long = 0) =
	read(address, Long.SIZE_BYTES.toLong())?.getLong(offset) ?: 0L

fun ReadableSource.float(address: Long, offset: Long = 0) =
	read(address, Float.SIZE_BYTES.toLong())?.getFloat(offset) ?: 0F

fun ReadableSource.double(address: Long, offset: Long = 0) =
	read(address, Double.SIZE_BYTES.toLong())?.getDouble(offset) ?: 0.0

fun ReadableSource.char(address: Long, offset: Long = 0) =
	read(address, Char.SIZE_BYTES.toLong())?.getChar(offset) ?: 0.toChar()

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