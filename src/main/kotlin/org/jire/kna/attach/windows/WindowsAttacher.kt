package org.jire.kna.attach.windows

import com.sun.jna.Native
import com.sun.jna.platform.win32.Tlhelp32
import org.jire.kna.Config
import org.jire.kna.attach.AbstractAttacher
import org.jire.kna.attach.AttachAccess
import org.jire.kna.attach.AttachedProcess
import com.sun.jna.platform.win32.Kernel32.INSTANCE as JNAKernel32

object WindowsAttacher : AbstractAttacher() {
	
	override fun defaultAttachAccess() = WindowsAttachAccess.All
	
	override fun byName(config: Config, processName: String, access: AttachAccess): AttachedProcess? {
		access as WindowsAttachAccess
		
		val snapshot = JNAKernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPALL, WindowsAttachUtils.DWORD_ZERO)
		val entry = Tlhelp32.PROCESSENTRY32.ByReference() // we reuse the same entry during iteration
		try {
			while (JNAKernel32.Process32Next(snapshot, entry)) {
				val fileName = Native.toString(entry.szExeFile)
				if (processName == fileName) return byID(config, entry.th32ProcessID.toInt(), access)
			}
		} finally {
			JNAKernel32.CloseHandle(snapshot)
		}
		return null
	}
	
	override fun byID(config: Config, processID: Int, access: AttachAccess): AttachedProcess? {
		access as WindowsAttachAccess
		
		val handle = JNAKernel32.OpenProcess(access.combinedAccessFlags, true, processID) ?: return null
		return WindowsAttachedProcess(config, handle)
	}
	
}