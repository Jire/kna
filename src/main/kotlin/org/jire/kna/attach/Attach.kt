@file:JvmName("Attach")

package org.jire.kna.attach

import com.sun.jna.Platform
import org.jire.kna.attach.windows.WindowsAttacher

val Attach by lazy(LazyThreadSafetyMode.NONE) {
	when {
		Platform.isWindows() || Platform.isWindowsCE() -> WindowsAttacher
		else -> throw NullPointerException("No attacher available for the current platform")
	}
}