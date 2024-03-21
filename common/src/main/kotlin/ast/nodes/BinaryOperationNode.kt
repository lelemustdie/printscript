package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor
import org.example.token.Token
@Serializable
data class BinaryOperationNode(private val value: Token, private val leftChild: Node, private val rightChild: Node): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
}