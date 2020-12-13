package org.jire.kna.attach

interface Attacher {
	
	fun attachProcessByName(processName: String): AttachedProcess
	
	fun attachProcessByID(processID: Int): AttachedProcess
	
}