package org.jire.kna

import com.sun.jna.Native
import net.openhft.chronicle.core.OS

inline class Pointer(override val address: Long) : Addressed {
	
	val jna get() = JNAPointerCache[address]
	
	fun offsetPointer(offset: Long) = Pointer(address + offset)
	
	fun readable() = address != NULL_ADDRESS
	fun ensureReadable() = apply { if (!readable()) throw IllegalStateException() }
	fun readableNullable() = if (readable()) this else null
	fun readableOr(otherValue: Pointer) = if (readable()) this else otherValue
	
	fun getByte(offset: Long) = OS.memory().readByte(address + offset)
	fun getShort(offset: Long) = OS.memory().readShort(address + offset)
	fun getInt(offset: Long) = OS.memory().readInt(address + offset)
	fun getLong(offset: Long) = OS.memory().readLong(address + offset)
	fun getFloat(offset: Long) = OS.memory().readFloat(address + offset)
	fun getDouble(offset: Long) = OS.memory().readDouble(address + offset)
	fun getChar(offset: Long) = getShort(offset).toChar()
	fun getBoolean(offset: Long) = getByte(offset).toInt() != 0
	
	fun getString(offset: Long): String = jna.getString(offset)
	
	fun read(offset: Long, buf: ByteArray?, index: Int, length: Int) = jna.read(offset, buf, index, length)
	
	fun setByte(offset: Long, value: Byte) = OS.memory().writeByte(address + offset, value)
	fun setShort(offset: Long, value: Short) = OS.memory().writeShort(address + offset, value)
	
	fun setInt(offset: Long, value: Int) = OS.memory().writeInt(address + offset, value)
	fun setLong(offset: Long, value: Long) = OS.memory().writeLong(address + offset, value)
	fun setFloat(offset: Long, value: Float) = OS.memory().writeFloat(address + offset, value)
	fun setDouble(offset: Long, value: Double) = OS.memory().writeDouble(address + offset, value)
	fun setChar(offset: Long, value: Char) = setShort(offset, value.toShort())
	fun setBoolean(offset: Long, value: Boolean) = setByte(offset, if (value) 1 else 0)
	
	companion object {
		const val NULL_ADDRESS = -1L
		val NULL = Pointer(NULL_ADDRESS)
		
		fun alloc(size: Long) = Pointer(Native.malloc(size))
	}
	
}