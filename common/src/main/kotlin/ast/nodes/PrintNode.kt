package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor

@Serializable
data class PrintNode(private val printable: Node): Node() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

}