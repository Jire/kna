package org.jire.kna.attach

import org.jire.kna.Pointer

abstract class AbstractAttachedModule(
	override val address: Long,
	override val process: AttachedProcess
) : AttachedModule {
	
	override fun read(address: Long, data: Pointer, bytesToRead: Long) = process.read(address, data, bytesToRead)
	
	override fun write(address: Long, data: Pointer, bytesToWrite: Long) = process.write(address, data, bytesToWrite)
	
}