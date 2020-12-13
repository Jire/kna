package org.jire.kna.attach.windows

import com.sun.jna.Native
import com.sun.jna.platform.win32.Tlhelp32
import org.jire.kna.attach.AttachAccess
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.attach.Attacher
import com.sun.jna.platform.win32.Kernel32.INSTANCE as JNAKernel32

object WindowsAttacher : Attacher {
	
	override fun byName(processName: String, attachAccess: AttachAccess): AttachedProcess? {
		attachAccess as WindowsAttachAccess
		
		val snapshot = JNAKernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPALL, WindowsAttachUtils.DWORD_ZERO)
		val entry = Tlhelp32.PROCESSENTRY32.ByReference() // we reuse the same entry during iteration
		try {
			while (JNAKernel32.Process32Next(snapshot, entry)) {
				val fileName = Native.toString(entry.szExeFile)
				if (processName == fileName) return byID(entry.th32ProcessID.toInt(), attachAccess)
			}
		} finally {
			JNAKernel32.CloseHandle(snapshot)
		}
		return null
	}
	
	override fun byID(processID: Int, attachAccess: AttachAccess): AttachedProcess? {
		attachAccess as WindowsAttachAccess
		
		val handle = JNAKernel32.OpenProcess(attachAccess.combinedAccessFlags, true, processID) ?: return null
		return WindowsAttachedProcess(handle)
	}
	
}