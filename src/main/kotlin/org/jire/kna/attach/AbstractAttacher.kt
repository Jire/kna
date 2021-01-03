package org.jire.kna.attach

abstract class AbstractAttacher : Attacher {
	
	private fun defaultConfig(initConfig: (AttachConfig.() -> Unit)?): AttachConfig =
		DefaultAttachConfig().apply { if (initConfig != null) initConfig() }
	
	protected abstract fun byID(
		config: AttachConfig,
		processID: Int,
		access: AttachAccess
	): AttachedProcess?
	
	protected abstract fun byName(
		config: AttachConfig,
		processName: String,
		access: AttachAccess
	): AttachedProcess?
	
	override fun byID(
		processID: Int,
		access: AttachAccess,
		initConfig: (AttachConfig.() -> Unit)?
	) = byID(defaultConfig(initConfig), processID, access)
	
	override fun byName(
		processName: String,
		access: AttachAccess,
		initConfig: (AttachConfig.() -> Unit)?
	) = byName(defaultConfig(initConfig), processName, access)
	
}