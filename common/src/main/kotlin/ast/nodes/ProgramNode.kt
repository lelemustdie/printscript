package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
data class ProgramNode(private val statements: List<Node>): Node() {
    fun getStatements(): List<Node> {
        return statements
    }
}