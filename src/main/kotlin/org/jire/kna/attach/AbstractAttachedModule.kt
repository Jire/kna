package org.jire.kna.attach

import com.sun.jna.Pointer

abstract class AbstractAttachedModule(
	override val address: Long,
	override val process: AttachedProcess
) : AttachedModule {
	
	override fun read(address: Pointer, data: Pointer, bytesToRead: Long) = process.read(address, data, bytesToRead)
	
	override fun write(address: Pointer, data: Pointer, bytesToWrite: Long) = process.write(address, data, bytesToWrite)
	
}