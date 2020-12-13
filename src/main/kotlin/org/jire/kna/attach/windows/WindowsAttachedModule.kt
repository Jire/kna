package org.jire.kna.attach.windows

import org.jire.kna.attach.AbstractAttachedModule

class WindowsAttachedModule(
	process: WindowsAttachedProcess,
	override val name: String,
	override val size: Long
) : AbstractAttachedModule(process)