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