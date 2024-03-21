package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.token.Token

@Serializable
data class LiteralNode(private val token: Token): ExpressionNode() {

}