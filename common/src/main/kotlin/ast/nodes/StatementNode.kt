package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
sealed class StatementNode: Node() {
    @Serializable
    data class AssignationNode(val identifier: ExpressionNode.IdentifierNode, val expression: ExpressionNode) : StatementNode()

    @Serializable
    data class VariableNode(val identifier: ExpressionNode.IdentifierNode, val dataType: ExpressionNode.TypeNode): StatementNode()

    @Serializable
    data class DeclarationNode(val variable: VariableNode, val expression: ExpressionNode): StatementNode()

    @Serializable
    data class PrintNode( val printable: Node): Node()

}