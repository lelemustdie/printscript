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

task("copyPreCommitGitHook", type = Copy::class) {
    from(".scripts/pre-commit")
    into(".git/hooks")
    fileMode = 493
}

tasks {
    build {
        dependsOn("copyPreCommitGitHook")
    }
}
