package org.jire.kna.attach

interface AttachedModules {
	
	fun attach(process: AttachedProcess)
	
	fun byName(moduleName: String): AttachedModule?
	
}