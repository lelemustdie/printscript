package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
sealed class StatementNode : Node() {
    @Serializable
    data class AssignationNode(val identifier: ExpressionNode.IdentifierNode, val expression: Node) : StatementNode()

    @Serializable
    data class VariableNode(val identifier: ExpressionNode.IdentifierNode, val dataType: Node) : StatementNode()

    @Serializable
    data class DeclarationNode(val variable: VariableNode, val expression: Node) : StatementNode()

    @Serializable
    data class PrintNode(val printable: Node) : Node()
}
