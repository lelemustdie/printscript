package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor

class DeclarationNode(private val variable: Node,private val value : Node): StatementNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String {
        return "DeclarationNode(variable=$variable, value=$value)"
    }

}