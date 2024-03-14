package org.example.ast.nodes

import org.example.ast.NodeVisitor

class IdentifierNode: ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
}