package org.example.ast.nodes

import org.example.ast.Node
import org.example.ast.NodeVisitor
import org.example.token.Token

class BinaryOperationNode(value: Token, rightChild : Node, leftChild: Node): ExpressionNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }

    val treeNode = value
    val rChild = rightChild
    val lChild = leftChild

}