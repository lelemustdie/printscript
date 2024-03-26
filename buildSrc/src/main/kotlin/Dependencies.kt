import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val tests = "org.jetbrains.kotlin:kotlin-test"
    const val jsonSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3"
}

fun DependencyHandler.tests() {
    testImplementation(Dependencies.tests)
}

fun DependencyHandler.kotlinxSerialization() {
    implementation(Dependencies.jsonSerialization)
}

fun DependencyHandler.common() {
    implementation(project(":common"))
}
fun DependencyHandler.lexer() {
    implementation(project(":lexer"))
}
fun DependencyHandler.parser() {
    implementation(project(":parser"))
}