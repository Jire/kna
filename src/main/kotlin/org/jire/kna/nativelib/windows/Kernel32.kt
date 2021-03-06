package org.jire.kna.nativelib.windows

import com.sun.jna.Pointer
import org.jire.kna.nativelib.DirectNativeLib

object Kernel32 : DirectNativeLib("Kernel32") {
	
	@JvmStatic
	external fun ReadProcessMemory(
		hProcess: Pointer,
		lpBaseAddress: Long,
		lpBuffer: Long,
		nSize: Int,
		lpNumberOfBytesRead: Int
	): Long
	
	@JvmStatic
	external fun WriteProcessMemory(
		hProcess: Pointer,
		lpBaseAddress: Long,
		lpBuffer: Long,
		nSize: Int,
		lpNumberOfBytesWritten: Int
	): Long
	
}