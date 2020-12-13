package org.jire.kna.nativelibs.windows

import org.jire.kna.nativelibs.DirectNativeLib

object User32 : DirectNativeLib("user32") {
	
	@JvmStatic
	external fun GetKeyState(nVirtKey: Int): Short
	
}