plugins {
    id("custom-plugin")
    id("org.jetbrains.kotlinx.kover") version "0.7.5"
    id("io.github.usefulness.ktlint-gradle-plugin") version "0.8.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    common()
    lexer()
    parser()
    interpreter()
    kover(project(":lexer"))
    kover(project(":parser"))
    implementation("io.github.usefulness:ktlint-gradle-plugin:0.8.2")
}



kotlin {
    jvmToolchain(21)
}

koverReport {
    filters {
        excludes {
            classes("org.example.MainKt")
        }
    }

    verify {
        rule {
            isEnabled = true
            bound {
                minValue = 80
            }
        }
    }
}