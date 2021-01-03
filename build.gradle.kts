import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.4.21"
	java
	`java-library`
	`maven-publish`
	id("com.github.dcendents.android-maven") version "2.1" apply true
	id("com.jfrog.bintray") version "1.8.5" apply true
	id("me.champeau.gradle.jmh") version "0.5.2"
}

val PROJECT_NAME = "kna"
val PROJECT_DESCRIPTION = "High-performance easy native access from Kotlin"
val PROJECT_GROUP = "org.jire"
val PROJECT_ARTIFACT = "kna"
val PROJECT_VERSION = "0.4.2"
val PROJECT_LICENSE = "Affero General Public License 3.0"
val PROJECT_LICENSE_TAG = "AGPL-V3"
val PROJECT_LICENSE_URL = "https://www.gnu.org/licenses/agpl-3.0.txt"

val YOUR_NAME = "Thomas Nappo"
val YOUR_EMAIL = "thomasgnappo@gmail.com"
val YOUR_GITHUB_NAME = "Jire"

val GITHUB_REPO = "$YOUR_GITHUB_NAME/$PROJECT_NAME/"
val GITHUB_REPO_URL = "https://github.com/$GITHUB_REPO"

group = PROJECT_GROUP
version = PROJECT_VERSION

repositories {
	jcenter()
}

val jnaVersion = "5.6.0"

dependencies {
	implementation("net.java.dev.jna", "jna", jnaVersion)
	implementation("net.java.dev.jna", "jna-platform", jnaVersion)
	implementation("it.unimi.dsi", "fastutil", "8.4.4")
	implementation("net.openhft", "chronicle-core", "2.20.125")
	
	val jmhVersion = "1.25"
	implementation("org.openjdk.jmh", "jmh-core", jmhVersion)
	annotationProcessor("org.openjdk.jmh", "jmh-generator-annprocess", jmhVersion)
	jmh("org.openjdk.jmh", "jmh-core", jmhVersion)
	jmhAnnotationProcessor("org.openjdk.jmh", "jmh-generator-annprocess", jmhVersion)
}

java {
	withJavadocJar()
	withSourcesJar()
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = PROJECT_GROUP
			artifactId = PROJECT_NAME
			version = PROJECT_VERSION
			from(components["java"])
			versionMapping {
				usage("java-api") {
					fromResolutionOf("runtimeClasspath")
				}
				usage("java-runtime") {
					fromResolutionResult()
				}
			}
			pom.withXml {
				asNode().apply {
					appendNode("description", PROJECT_DESCRIPTION)
					appendNode("name", rootProject.name)
					appendNode("url", GITHUB_REPO_URL)
					appendNode("licenses").appendNode("license").apply {
						appendNode("name", PROJECT_LICENSE_TAG)
						appendNode("url", PROJECT_LICENSE_URL)
						appendNode("distribution", "repo")
					}
					appendNode("developers")
						.appendNode("developer").apply {
							appendNode("id", YOUR_GITHUB_NAME)
							appendNode("name", YOUR_NAME)
							appendNode("email", YOUR_EMAIL)
						}
					appendNode("scm").apply {
						appendNode("url", GITHUB_REPO_URL)
					}
				}
			}
		}
	}
}

tasks.javadoc {
	if (JavaVersion.current().isJava9Compatible) {
		(options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
	}
}

bintray {
	user = System.getenv("BINTRAY_USER")
	key = System.getenv("BINTRAY_API_KEY")
	setPublications("maven")
	pkg.run {
		repo = "maven"
		name = PROJECT_NAME
		userOrg = user
		setLicenses(PROJECT_LICENSE_TAG)
		publicDownloadNumbers = true
		githubRepo = GITHUB_REPO
		websiteUrl = GITHUB_REPO_URL
		issueTrackerUrl = "$GITHUB_REPO_URL/issues"
		vcsUrl = "$GITHUB_REPO_URL.git"
		description = PROJECT_DESCRIPTION
		setLabels("kotlin", "native", "access")
		desc = description
		
		version.run {
			name = PROJECT_VERSION
			vcsTag = PROJECT_VERSION
			gpg.apply {
				sign = true
			}
		}
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
		freeCompilerArgs = listOf("-Xinline-classes")
	}
}

jmh {
	duplicateClassesStrategy = DuplicatesStrategy.INCLUDE
}