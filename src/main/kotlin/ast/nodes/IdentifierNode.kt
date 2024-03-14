package org.example.ast.nodes

import org.example.ast.NodeVisitor
import org.example.token.Token

class IdentifierNode(id : Token?): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    val id = id
}