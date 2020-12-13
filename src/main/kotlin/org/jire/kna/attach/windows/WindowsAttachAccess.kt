package org.jire.kna.attach.windows

import com.sun.jna.platform.win32.WinNT
import org.jire.kna.attach.AttachAccess

open class WindowsAttachAccess(vararg accessFlags: Int) : AttachAccess {
	
	var combinedAccessFlags = 0
		private set
	
	init {
		for (accessFlag in accessFlags) combinedAccessFlags = combinedAccessFlags or accessFlag
	}
	
	object All : WindowsAttachAccess(WinNT.PROCESS_ALL_ACCESS)
	object Read : WindowsAttachAccess(WinNT.PROCESS_VM_READ)
	object Write : WindowsAttachAccess(WinNT.PROCESS_VM_WRITE)
	object ReadWrite : WindowsAttachAccess(WinNT.PROCESS_VM_READ, WinNT.PROCESS_VM_WRITE)
	
}