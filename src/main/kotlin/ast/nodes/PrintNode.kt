package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor

class PrintNode: Node {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
}