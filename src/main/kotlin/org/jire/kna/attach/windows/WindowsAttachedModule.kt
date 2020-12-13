package org.jire.kna.attach.windows

import com.sun.jna.Native
import com.sun.jna.platform.win32.Psapi.MODULEINFO
import com.sun.jna.platform.win32.WinDef
import org.jire.kna.attach.AbstractAttachedModule
import org.jire.kna.nativelib.windows.Psapi

class WindowsAttachedModule(
	address: Long,
	process: WindowsAttachedProcess,
	val handle: WinDef.HMODULE,
	val info: MODULEINFO
) : AbstractAttachedModule(address, process) {
	
	override val name by lazy {
		val baseName = ByteArray(256)
		Psapi.GetModuleBaseNameA(process.handle, handle, baseName, baseName.size)
		Native.toString(baseName)!!
	}
	
	override val size = info.SizeOfImage.toLong()
	
	override fun equals(other: Any?) = this === other
			|| (other is WindowsAttachedModule
			&& handle == other.handle
			&& info == other.info
			&& size == other.size)
	
	override fun hashCode(): Int {
		var result = handle.hashCode()
		result = 31 * result + info.hashCode()
		result = 31 * result + size.hashCode()
		return result
	}
	
	override fun toString(): String {
		return "WindowsAttachedModule(handle=$handle, info=$info, name='$name', size=$size)"
	}
	
}