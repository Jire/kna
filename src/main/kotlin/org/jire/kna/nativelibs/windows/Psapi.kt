package org.jire.kna.nativelibs.windows

import com.sun.jna.Native
import org.jire.kna.nativelibs.NativeLib

private const val PSAPI_LIBRARY_NAME = "Psapi"

object Psapi : NativeLib,
	PsapiStdCallLibrary by Native.load(
		PSAPI_LIBRARY_NAME,
		PsapiStdCallLibrary::class.java
	) {
	
	override val libraryName: String = PSAPI_LIBRARY_NAME
	
	object FilterFlags {
		const val LIST_MODULES_32BIT = 0x01
		const val LIST_MODULES_64BIT = 0x02
		const val LIST_MODULES_ALL = 0x03
		const val LIST_MODULES_DEFAULT = 0x0
	}
	
}