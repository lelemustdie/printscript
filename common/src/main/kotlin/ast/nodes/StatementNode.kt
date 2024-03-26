package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
sealed class StatementNode: Node() {
    @Serializable
    data class AssignationNode(val identifier: DeclarationNode, val expression: Node) : StatementNode() {

    }

    @Serializable
    data class DeclarationNode(val identifier: ExpressionNode.IdentifierNode, val dataType: Node): StatementNode(){

    }

}