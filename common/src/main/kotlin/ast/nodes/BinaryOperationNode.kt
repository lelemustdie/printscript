package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.token.Token
@Serializable
data class BinaryOperationNode(private val value: Token, private val leftChild: Node, private val rightChild: Node): ExpressionNode() {

}