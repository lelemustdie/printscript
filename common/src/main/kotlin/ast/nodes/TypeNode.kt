package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.token.Token

@Serializable
data class TypeNode(private val typeToken: Token): ExpressionNode() {
}