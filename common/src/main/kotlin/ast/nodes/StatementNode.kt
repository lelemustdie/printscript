package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
sealed class StatementNode: Node() {
    @Serializable
    data class AssignationNode(private val identifier: StatementNode, private val expression: Node) : StatementNode() {

    fun getId(): StatementNode {
        return identifier
    }

    fun getExpression(): Node {
        return expression
    }
}


}