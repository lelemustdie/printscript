package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor

@Serializable
data class DeclarationNode(private val variable: Node, private val value : Node): StatementNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

}