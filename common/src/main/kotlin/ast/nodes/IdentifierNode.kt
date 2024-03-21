package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor
import org.example.token.Token
@Serializable
data class IdentifierNode(private val id : Token): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
}