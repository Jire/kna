package org.jire.kna.attach

abstract class AbstractAttachedProcess : AttachedProcess {
	
	override val address = 0L
	
	abstract val modules: AttachedModules
	
	override fun modules(attach: Boolean) = modules.apply { if (attach) attach(this@AbstractAttachedProcess) }
	
}