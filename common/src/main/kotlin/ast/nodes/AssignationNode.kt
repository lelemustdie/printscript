package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor
@Serializable
data class AssignationNode(private val identifier: Node, private val expression: Node): StatementNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
}