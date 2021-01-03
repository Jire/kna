package org.jire.kna.attach.windows

import com.sun.jna.Memory
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import org.jire.kna.attach.AttachConfig
import org.jire.kna.attach.AttachConfigKey
import org.jire.kna.attach.CachedAttachedProcess
import org.jire.kna.attach.DefaultAttachConfigKey
import org.jire.kna.nativelib.windows.Kernel32
import org.jire.kna.nativelib.windows.NTDLL

class WindowsAttachedProcess(config: AttachConfig, val handle: WinNT.HANDLE) : CachedAttachedProcess(config) {
	
	companion object {
		val KERNEL32_READS: AttachConfigKey<Boolean> = DefaultAttachConfigKey(true)
		val KERNEL32_WRITES: AttachConfigKey<Boolean> = DefaultAttachConfigKey(false)
	}
	
	val kernel32Reads = config[KERNEL32_READS]
	val kernel32Writes = config[KERNEL32_WRITES]
	
	override val modules = WindowsAttachedModules()
	
	// we need these separate so it doesn't override writes of size 8
	private val readMemory = Memory(8)
	private val writtenMemory = Memory(8)
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long): Boolean {
		if (kernel32Reads) {
			return Kernel32.ReadProcessMemory(handle.pointer, address, data, bytesToRead.toInt(), 0) > 0
		}
		NTDLL.NtReadVirtualMemory(handle.pointer, address, data, bytesToRead, readMemory)
		val readBytes = readMemory.getLong(0)
		return readBytes != 0L
	}
	
	override fun write(address: Pointer, data: Pointer, bytesToWrite: Long): Boolean {
		if (kernel32Writes) {
			return Kernel32.WriteProcessMemory(handle.pointer, address, data, bytesToWrite.toInt(), 0) > 0
		}
		NTDLL.NtWriteVirtualMemory(handle.pointer, address, data, bytesToWrite, writtenMemory)
		val writtenBytes = writtenMemory.getLong(0)
		return writtenBytes != 0L
	}
	
}