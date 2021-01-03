package org.jire.kna.attach

import org.jire.kna.Config
import org.jire.kna.DefaultConfig

interface Attacher {
	
	fun defaultAttachAccess(): AttachAccess
	
	fun byName(
		config: Config,
		processName: String,
		access: AttachAccess = defaultAttachAccess()
	): AttachedProcess?
	
	fun byID(
		config: Config,
		processID: Int,
		access: AttachAccess = defaultAttachAccess()
	): AttachedProcess?
	
	companion object {
		private fun defaultConfig(initConfig: (Config.() -> Unit)?): Config = DefaultConfig().apply {
			if (initConfig != null) initConfig()
		}
	}
	
	fun byID(
		processID: Int,
		access: AttachAccess,
		initConfig: (Config.() -> Unit)?
	) = byID(defaultConfig(initConfig), processID, access)
	
	fun byName(
		processName: String,
		access: AttachAccess,
		initConfig: (Config.() -> Unit)?
	) = byName(defaultConfig(initConfig), processName, access)
	
}