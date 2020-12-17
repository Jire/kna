package org.jire.kna.attach.windows

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import org.jire.kna.MemoryCache
import org.jire.kna.attach.AbstractAttachedProcess
import org.jire.kna.nativelib.windows.NTDLL

class WindowsAttachedProcess(val handle: WinNT.HANDLE) : AbstractAttachedProcess() {
	
	override val modules = WindowsAttachedModules()
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long): Boolean {
		val readMemory = MemoryCache[8]
		NTDLL.NtReadVirtualMemory(handle.pointer, address, data, bytesToRead, readMemory)
		val readBytes = readMemory.getLong(0)
		return readBytes != 0L
	}
	
	override fun write(address: Pointer, data: Pointer, bytesToWrite: Long): Boolean {
		val writtenMemory = MemoryCache[8]
		NTDLL.NtWriteVirtualMemory(handle.pointer, address, data, bytesToWrite, writtenMemory)
		val writtenBytes = writtenMemory.getLong(0)
		return writtenBytes != 0L
	}
	
}