package org.jire.kna.attach

import org.jire.kna.Addressed
import org.jire.kna.ReadableSource
import org.jire.kna.WritableSource

interface AttachedModule : Addressed, ReadableSource, WritableSource {
	
	val process: AttachedProcess
	
	val name: String
	
	val size: Long
	
}