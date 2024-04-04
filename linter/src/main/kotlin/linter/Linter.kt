package linter

import org.example.ast.nodes.ProgramNode

interface Linter {
    fun checkErrors(ast: ProgramNode): List<Error>
}
