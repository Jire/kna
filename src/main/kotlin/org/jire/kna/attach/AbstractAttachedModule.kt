package org.jire.kna.attach

import org.jire.kna.PointerCache
import org.jire.kna.ReadableSource
import org.jire.kna.WritableSource

abstract class AbstractAttachedModule(
	override val address: Long,
	override val process: AttachedProcess
) : AttachedModule,
	ReadableSource by process,
	WritableSource by process {
	
	override fun offset(offset: Long) = address + offset
	
	override fun addressToPointer(address: Long) = PointerCache[offset(address)]
	
}