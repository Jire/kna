package org.jire.kna.attach.windows

import com.sun.jna.Memory
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import org.jire.kna.attach.AbstractAttachedProcess
import org.jire.kna.nativelib.windows.Kernel32
import org.jire.kna.nativelib.windows.NTDLL

class WindowsAttachedProcess(val handle: WinNT.HANDLE) : AbstractAttachedProcess() {
	
	@Volatile
	var kernel32Mode = false
	
	override val modules = WindowsAttachedModules()
	
	// we need these separate so it doesn't override writes of size 8
	private val readMemory = Memory(8)
	private val writtenMemory = Memory(8)
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long): Boolean {
		if (kernel32Mode) {
			return Kernel32.ReadProcessMemory(handle.pointer, address, data, bytesToRead.toInt(), 0) > 0
		}
		NTDLL.NtReadVirtualMemory(handle.pointer, address, data, bytesToRead, readMemory)
		val readBytes = readMemory.getLong(0)
		return readBytes != 0L
	}
	
	override fun write(address: Pointer, data: Pointer, bytesToWrite: Long): Boolean {
		if (kernel32Mode) {
			return Kernel32.WriteProcessMemory(handle.pointer, address, data, bytesToWrite.toInt(), 0) > 0
		}
		NTDLL.NtWriteVirtualMemory(handle.pointer, address, data, bytesToWrite, writtenMemory)
		val writtenBytes = writtenMemory.getLong(0)
		return writtenBytes != 0L
	}
	
}