package org.jire.kna.attach.windows

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import org.jire.kna.attach.AttachedModule
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.nativelibs.windows.NTDLL

class WindowsAttachedProcess(val handle: WinNT.HANDLE) : AttachedProcess {
	
	override fun attachModule(moduleName: String): AttachedModule {
		TODO("Not yet implemented")
	}
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long) =
		NTDLL.NtReadVirtualMemory(handle.pointer, address, data, bytesToRead) > 0
	
}