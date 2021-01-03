package org.jire.kna.attach

import org.jire.kna.Config
import org.jire.kna.ReadableSource
import org.jire.kna.WritableSource

interface AttachedProcess : ReadableSource, WritableSource {
	
	fun modules(attach: Boolean = true): AttachedModules
	
	val config: Config
	
}