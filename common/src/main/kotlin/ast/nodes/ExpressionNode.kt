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
    data class LiteralNode(private val token: Token) : ExpressionNode(){
        fun getToken(): Token {
            return token
        }
    }
    @Serializable
    data class IdentifierNode(private val id : Token): ExpressionNode(){
        fun getId(): Token {
            return id
        }
    }
    @Serializable
    data class TypeNode(private val typeToken: Token): ExpressionNode()
}