package org.jire.kna

import com.sun.jna.Pointer

inline class Data(override val address: Long) : Addressed {
	val pointer get() = Pointer(address)
}