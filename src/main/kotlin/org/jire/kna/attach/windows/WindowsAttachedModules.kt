package org.jire.kna.attach.windows

import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.Psapi.MODULEINFO
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.ptr.IntByReference
import org.jire.kna.attach.AbstractAttachedModules
import org.jire.kna.attach.AttachedProcess
import org.jire.kna.nativelib.windows.Psapi
import com.sun.jna.platform.win32.Psapi.INSTANCE as JNAPsapi

object WindowsAttachedModules : AbstractAttachedModules() {
	
	private const val MODULE_ENUMERATION_SIZE = 4096
	
	override fun attach(process: AttachedProcess) {
		process as WindowsAttachedProcess
		
		val modules = arrayOfNulls<WinDef.HMODULE>(MODULE_ENUMERATION_SIZE)
		val needed = IntByReference()
		if (!Psapi.EnumProcessModulesEx(process.handle, modules, modules.size, needed)) return
		
		for (i in 0..needed.value / Native.getNativeSize(WinDef.HMODULE::class.java)) {
			val hModule = modules[i] ?: continue
			val info = MODULEINFO()
			if (JNAPsapi.GetModuleInformation(process.handle, hModule, info, info.size())) {
				val address = Pointer.nativeValue(hModule.pointer)
				val module = WindowsAttachedModule(address, process, hModule, info)
				moduleNameToModule[module.name] = module
			}
		}
	}
	
}