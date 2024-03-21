package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
data class DeclarationNode(private val identifier: Node, private val dataType : Node): StatementNode() {

}