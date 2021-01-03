package org.jire.kna.attach

import org.jire.kna.Config
import org.jire.kna.DefaultConfig

abstract class AbstractAttacher : Attacher {
	
	private fun defaultConfig(initConfig: (Config.() -> Unit)?): Config =
		DefaultConfig().apply { if (initConfig != null) initConfig() }
	
	protected abstract fun byID(
		config: Config,
		processID: Int,
		access: AttachAccess
	): AttachedProcess?
	
	protected abstract fun byName(
		config: Config,
		processName: String,
		access: AttachAccess
	): AttachedProcess?
	
	override fun byID(
		processID: Int,
		access: AttachAccess,
		initConfig: (Config.() -> Unit)?
	) = byID(defaultConfig(initConfig), processID, access)
	
	override fun byName(
		processName: String,
		access: AttachAccess,
		initConfig: (Config.() -> Unit)?
	) = byName(defaultConfig(initConfig), processName, access)
	
}