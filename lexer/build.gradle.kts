plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.kover") version "0.7.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    tests()
    common()
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}