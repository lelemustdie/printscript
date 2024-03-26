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

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}