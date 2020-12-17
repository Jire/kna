package org.jire.kna.attach

interface Attacher {
	
	fun defaultAttachAccess(): AttachAccess
	
	fun byName(
		processName: String,
		attachAccess: AttachAccess = defaultAttachAccess()
	): AttachedProcess?
	
	fun byID(
		processID: Int,
		attachAccess: AttachAccess = defaultAttachAccess()
	): AttachedProcess?
	
}