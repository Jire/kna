package org.jire.kna.nativelib.windows

import com.sun.jna.Pointer
import org.jire.kna.nativelib.DirectNativeLib

object NTDLL : DirectNativeLib("ntdll") {
	
	@JvmStatic
	external fun NtReadVirtualMemory(
		processHandle: Pointer,
		baseAddress: Pointer,
		buffer: Pointer,
		numberOfBytesToRead: Long,
		numberOfBytesReaded: Pointer
	)
	
	@JvmStatic
	external fun NtWriteVirtualMemory(
		processHandle: Pointer,
		baseAddress: Pointer,
		buffer: Pointer,
		numberOfBytesToWrite: Long,
		numberOfBytesWritten: Pointer
	)
	
}