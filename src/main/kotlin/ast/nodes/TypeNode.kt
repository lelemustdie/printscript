package org.example.ast.nodes

import org.example.ast.NodeVisitor
import org.example.token.Token

class TypeNode(private val type: Token): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String {
        return "TypeNode(type=$type)"
    }
}