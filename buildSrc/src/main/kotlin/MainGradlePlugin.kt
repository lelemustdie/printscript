import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*

class MainGradlePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
    }

    private fun applyPlugins(project: Project) {

        project.apply {
            plugin("kotlinx-serialization")
        }

        project.repositories {
            mavenCentral()
        }
        project.dependencies {
            tests()
            common()
            lexer()
            parser()
            kotlinxSerialization()
        }
        project.tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}