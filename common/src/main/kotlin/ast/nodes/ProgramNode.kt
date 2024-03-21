package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor

@Serializable
data class ProgramNode(private val statements: List<Node>): Node() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

}