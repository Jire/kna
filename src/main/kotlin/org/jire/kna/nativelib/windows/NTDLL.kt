package org.jire.kna.nativelib.windows

import com.sun.jna.Pointer
import org.jire.kna.nativelib.DirectNativeLib

object NTDLL : DirectNativeLib("ntdll") {
	
	@JvmStatic
	external fun NtReadVirtualMemory(
		processHandle: Pointer,
		baseAddress: Long,
		buffer: Long,
		numberOfBytesToRead: Long,
		numberOfBytesReaded: Pointer
	)
	
	@JvmStatic
	external fun NtWriteVirtualMemory(
		processHandle: Pointer,
		baseAddress: Long,
		buffer: Long,
		numberOfBytesToWrite: Long,
		numberOfBytesWritten: Pointer
	)
	
}