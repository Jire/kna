package org.jire.kna.nativelibs.windows

import org.jire.kna.nativelibs.NativeLib

object User32 : NativeLib("user32") {
	
	@JvmStatic
	external fun GetKeyState(nVirtKey: Int): Short
	
}