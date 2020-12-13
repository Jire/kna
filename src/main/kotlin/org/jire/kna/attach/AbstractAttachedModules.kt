package org.jire.kna.attach

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
import it.unimi.dsi.fastutil.objects.Object2ObjectMap

abstract class AbstractAttachedModules : AttachedModules {
	
	val moduleNameToModule: Object2ObjectMap<String, AttachedModule> = Object2ObjectArrayMap()
	
	override fun byName(moduleName: String) = moduleNameToModule[moduleName]
	
}