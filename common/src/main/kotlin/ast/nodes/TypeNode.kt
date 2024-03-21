package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor
import org.example.token.Token

@Serializable
data class TypeNode(private val typeToken: Token): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

}