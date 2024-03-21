package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
sealed class StatementNode: Node() {
    @Serializable
    data class AssignationNode(private val identifier: Node,
                               private val expression: Node): StatementNode()

}