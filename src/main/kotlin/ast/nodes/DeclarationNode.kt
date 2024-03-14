package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor

class DeclarationNode(Variable: Node, Value : Node): StatementNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    val variable = Variable
    val value = Value

}