import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.4.21"
	id("com.github.dcendents.android-maven") version "2.1" apply true
}

group = "org.jire"
version = "0.1.1"

repositories {
	jcenter()
}

val jnaVersion = "5.6.0"

dependencies {
	implementation("net.java.dev.jna", "jna", jnaVersion)
	implementation("net.java.dev.jna", "jna-platform", jnaVersion)
	implementation("it.unimi.dsi", "fastutil", "8.4.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "13"
}