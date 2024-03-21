package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor
@Serializable
class AssignationNode: StatementNode() {
    override fun <T> accept(visitor: NodeVisitor<T>): T {
        return visitor.visit(this)
    }
}