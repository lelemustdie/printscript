package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor

class ProgramNode(private val statements: List<Node>): Node {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String {
        return "ProgramNode(statements=$statements)"
    }
}