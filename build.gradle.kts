plugins {
    id("custom-plugin")
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    common()
    lexer()
    parser()
    interpreter()
}

kotlin {
    jvmToolchain(21)
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
