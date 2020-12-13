package org.jire.kna.nativelibs.windows

import com.sun.jna.Native

object Psapi : PsapiStdCallLibrary by Native.load("Psapi", PsapiStdCallLibrary::class.java) {
	
	object FilterFlags {
		const val LIST_MODULES_32BIT = 0x01
		const val LIST_MODULES_64BIT = 0x02
		const val LIST_MODULES_ALL = 0x03
		const val LIST_MODULES_DEFAULT = 0x0
	}
	
}