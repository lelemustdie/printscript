plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.22")
    implementation("io.github.usefulness:ktlint-gradle-plugin:0.9.0")
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.6")
}