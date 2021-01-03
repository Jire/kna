package org.jire.kna.attach

import org.jire.kna.Config

abstract class AbstractAttachedProcess(override val config: Config) : AttachedProcess {
	
	override val address = 0L
	
	abstract val modules: AttachedModules
	
	override fun modules(attach: Boolean) = modules.apply { if (attach) attach(this@AbstractAttachedProcess) }
	
}