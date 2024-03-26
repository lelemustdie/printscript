plugins {
    kotlin("jvm")
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

kotlin {
    jvmToolchain(21)
}