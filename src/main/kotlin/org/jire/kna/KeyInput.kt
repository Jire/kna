package org.jire.kna

import com.sun.jna.Platform
import org.jire.kna.nativelib.windows.User32

object KeyInput {
	
	fun state(virtualKeyCode: Int) = when {
		Platform.isWindows() || Platform.isWindowsCE() -> User32.GetKeyState(virtualKeyCode).toInt()
		else -> throw UnsupportedOperationException("Unsupported platform (osType=${Platform.getOSType()}")
	}
	
	fun pressed(virtualKeyCode: Int) = state(virtualKeyCode) < 0
	
	fun released(virtualKeyCode: Int) = !pressed(virtualKeyCode)
	
}