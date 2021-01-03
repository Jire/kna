package org.jire.kna.attach

import org.jire.kna.Config

interface Attacher {
	
	fun defaultAttachAccess(): AttachAccess
	
	fun byName(
		processName: String,
		access: AttachAccess = defaultAttachAccess(),
		initConfig: (Config.() -> Unit)? = null
	): AttachedProcess?
	
	fun byID(
		processID: Int,
		access: AttachAccess = defaultAttachAccess(),
		initConfig: (Config.() -> Unit)? = null
	): AttachedProcess?
	
}