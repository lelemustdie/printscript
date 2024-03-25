package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
data class DeclarationNode(private val identifier: ExpressionNode.IdentifierNode, private val dataType : Node): StatementNode() {
    fun getIdentifier(): ExpressionNode.IdentifierNode {
        return identifier
    }

    fun getDataType(): Node {
        return dataType
    }

}