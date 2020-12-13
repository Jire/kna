package org.jire.kna.nativelib.windows

import org.jire.kna.nativelib.DirectNativeLib

object User32 : DirectNativeLib("user32") {
	
	@JvmStatic
	external fun GetKeyState(nVirtKey: Int): Short
	
}