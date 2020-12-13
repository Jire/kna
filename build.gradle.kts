import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "org.jire"
version = "0.1.0"

repositories {
    jcenter()
}

val jnaVersion = "5.6.0"

dependencies {
    implementation("net.java.dev.jna", "jna", jnaVersion)
    implementation("net.java.dev.jna", "jna-platform", jnaVersion)
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}