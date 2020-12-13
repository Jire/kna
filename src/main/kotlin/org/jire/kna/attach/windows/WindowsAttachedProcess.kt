package org.jire.kna.attach.windows

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.nativelibs.windows.NTDLL

class WindowsAttachedProcess(val handle: WinNT.HANDLE) : AttachedProcess {
	
	override fun modules(attach: Boolean) =
		WindowsAttachedModules.apply { if (attach) attach(this@WindowsAttachedProcess) }
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long) =
		NTDLL.NtReadVirtualMemory(handle.pointer, address, data, bytesToRead) > 0
	
}