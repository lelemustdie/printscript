package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor
import org.example.token.Token

class BinaryOperationNode(private val value: Token, private val leftChild: Node, private val rightChild: Node): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    override fun toString(): String {
        return "BinaryOperationNode(value=$value, leftChild=$leftChild, rightChild=$rightChild)"
    }

}