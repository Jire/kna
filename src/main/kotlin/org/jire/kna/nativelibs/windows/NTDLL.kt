package org.jire.kna.nativelibs.windows

import com.sun.jna.Pointer
import org.jire.kna.nativelibs.NativeLib

object NTDLL : NativeLib("ntdll") {
	
	@JvmStatic
	external fun NtReadVirtualMemory(
		processHandle: Pointer,
		baseAddress: Pointer,
		buffer: Pointer,
		numberOfBytesToRead: Long
	): Long
	
	@JvmStatic
	external fun NtWriteVirtualMemory(
		processHandle: Pointer,
		baseAddress: Pointer,
		buffer: Pointer,
		numberOfBytesToWrite: Long
	): Long
	
}