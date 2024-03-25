package org.example.ast.nodes

import kotlinx.serialization.Serializable

@Serializable
data class PrintNode(private val printable: Node): Node() {
    fun getPrintable(): Node {
        return printable
    }
}