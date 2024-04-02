plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    id("org.jlleitschuh.gradle.ktlint")
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
    filters {
        excludes {
            //exclude main classes
            classes("org.example.MainKt")
        }
    }
    verify {
        rule {
            isEnabled = false
            bound {
                minValue = 80
            }
        }
    }
}

