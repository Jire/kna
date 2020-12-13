package org.jire.kna.nativelibs

import com.sun.jna.Native
import com.sun.jna.NativeLibrary

abstract class NativeLib(val libraryName: String) {
	
	init {
		Native.register(javaClass, NativeLibrary.getInstance(libraryName))
		//Native.register(NativeLibrary.getInstance(libraryName))
	}
	
}