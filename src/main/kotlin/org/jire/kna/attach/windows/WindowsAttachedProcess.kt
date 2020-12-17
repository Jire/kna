package org.jire.kna.attach.windows

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import org.jire.kna.attach.AbstractAttachedProcess
import org.jire.kna.nativelib.windows.NTDLL

class WindowsAttachedProcess(val handle: WinNT.HANDLE) : AbstractAttachedProcess() {
	
	override val modules = WindowsAttachedModules()
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long) =
		NTDLL.NtReadVirtualMemory(handle.pointer, address, data, bytesToRead) > 0
	
	override fun write(address: Pointer, data: Pointer, bytesToWrite: Long) =
		NTDLL.NtWriteVirtualMemory(handle.pointer, address, data, bytesToWrite) > 0
	
}