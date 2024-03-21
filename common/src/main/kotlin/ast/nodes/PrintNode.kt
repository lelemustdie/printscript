package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor

class PrintNode(private val printable: Node): Node {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String {
        return "PrintNode(printable=$printable)"
    }
}