package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.token.Token
@Serializable
sealed class ExpressionNode: Node() {
    @Serializable
    data class BinaryOperationNode(private val value: Token,
                                   private val leftChild: Node,
                                   private val rightChild: Node): ExpressionNode()
    @Serializable
    data class LiteralNode(val token: Token) : ExpressionNode(){
    }
    @Serializable
    data class IdentifierNode(val id : Token): ExpressionNode(){
    }
    @Serializable
    data class TypeNode(private val typeToken: Token): ExpressionNode()
}