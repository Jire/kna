package org.jire.kna.attach

interface Attacher {
	
	fun byName(
		processName: String,
		attachAccess: AttachAccess
	): AttachedProcess?
	
	fun byID(
		processID: Int,
		attachAccess: AttachAccess
	): AttachedProcess?
	
}