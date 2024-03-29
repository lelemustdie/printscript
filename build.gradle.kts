plugins {
    id("custom-plugin")
    kotlin("plugin.serialization") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    common()
    lexer()
    parser()
    kotlinxSerialization()
    implementation(project(":interpreter"))
}