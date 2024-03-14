package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor

class ProgramNode(private val statements: List<Node>): Node {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
    fun getStatements(): List<Node> {
        return statements
    }
}