package org.jire.kna.attach

interface Attacher {
	
	fun defaultAttachAccess(): AttachAccess
	
	fun byName(
		processName: String,
		access: AttachAccess = defaultAttachAccess(),
		initConfig: (AttachConfig.() -> Unit)? = null
	): AttachedProcess?
	
	fun byID(
		processID: Int,
		access: AttachAccess = defaultAttachAccess(),
		initConfig: (AttachConfig.() -> Unit)? = null
	): AttachedProcess?
	
}