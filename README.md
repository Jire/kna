# KNA (Kotlin Native Access)

_High-performance easy native access from Kotlin_

[![JitPack](https://jitpack.io/v/org.jire/kna.svg)](https://jitpack.io/#org.jire/kna)
[![Build Status](https://travis-ci.com/Jire/kna.svg?branch=master)](https://travis-ci.com/Jire/kna)
[![License](https://img.shields.io/github/license/Jire/kna.svg)](https://github.com/Jire/kna/blob/master/LICENSE.txt)

Currently still under development -- documentation and unit tests are missing.

---

## Using from Gradle

First add the JitPack repository:

Gradle Kotlin:

```kotlin
maven("https://jitpack.io")
```

Gradle Groovy:

```groovy
maven { url 'https://jitpack.io' }
```

Then add the KNA dependency:

Gradle Kotlin:

```kotlin
implementation("org.jire", "kna", "0.1.1")
```

Gradle Groovy:

```groovy
implementation 'org.jire:kna:0.1.1'
```

---

## Attaching to a process

You can attach to a process using a name (executable file name):

```kotlin
val process = Attach.byName("process.exe")!!
```

Or you can attach using a process ID (PID):

```kotlin
val process = Attach.byID(123)!!
```

## Attaching to modules of a process

You can use `.modules()` off a process to acquire attached modules.

```kotlin
val modules = process.modules()
```

Consider that each call to `.modules()` will reload all modules unless you specify `attach` as `false` like so:

```kotlin
val modules = process.modules(attach = false)
```

From the attached modules you can use `.byName(moduleName)` to select a certain module:

```kotlin
val module = modules.byName("module.dll")
```

## Reading from processes & modules

You can use the implicit data type to read from an address:

```kotlin
val someByte: Byte = process[0x100]
val someInt: Int = process[0x101]
val someFloat: Float = process[0x105]
val someBoolean: Boolean = process[0x109]
```

The implicit type also works when passing arguments or using if expressions:

```kotlin
if (process[0x321]) // Boolean type inferred
	sin(process[0x555]) // Double type inferred
```

Sometimes it's easier or necessary to use explicit types:

```kotlin
val someByte = process.byte(0x100)
val someInt = process.int(0x101)
val someFloat = process.float(0x105)
val someBoolean = process.boolean(0x109)
```

## Writing to processes & modules

You can use "implicit" writing with the set operator.

```kotlin
something[0x100] = 1.toByte()
something[0x101] = 1
something[0x105] = 1F
something[0x109] = true
```

There are no "explicit" writes, as the "implicit" writes are simply method overloads.