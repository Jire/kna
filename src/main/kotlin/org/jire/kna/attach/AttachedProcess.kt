package org.jire.kna.attach

import org.jire.kna.ReadableSource
import org.jire.kna.WritableSource

interface AttachedProcess : ReadableSource, WritableSource {
	
	fun attachModule(moduleName: String): AttachedModule
	
}