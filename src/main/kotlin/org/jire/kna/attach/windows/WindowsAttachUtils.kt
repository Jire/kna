package org.jire.kna.attach.windows

import com.sun.jna.platform.win32.WinDef

object WindowsAttachUtils {
	
	/**
	 * Reusable DWORD of value zero; not intended to be mutated.
	 */
	val DWORD_ZERO = WinDef.DWORD(0)
	
}