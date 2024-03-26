plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
}

apply<MainGradlePlugin>()

group = "org.example"
version = "1.0-SNAPSHOT"



kotlin {
    jvmToolchain(21)
}