package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.token.Token
@Serializable
data class IdentifierNode(private val id : Token): ExpressionNode() {

}