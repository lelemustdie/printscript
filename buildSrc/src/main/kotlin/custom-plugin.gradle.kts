plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    id("io.github.usefulness.ktlint-gradle-plugin")
}

repositories {
    mavenCentral()
}

dependencies {
    tests()
    kotlinxSerialization()
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

koverReport {
    verify {
        rule {
            isEnabled = true
            bound {
                minValue = 80
            }
        }
    }
}

