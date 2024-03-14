package org.example.ast.nodes

import org.example.ast.NodeVisitor
import org.example.token.Token

class TypeNode(type: Token): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    val type = type
}