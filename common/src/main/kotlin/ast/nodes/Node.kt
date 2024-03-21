package org.example.ast.nodes

import kotlinx.serialization.Serializable
import org.example.ast.NodeVisitor

@Serializable
sealed class Node {
    abstract fun <T> accept(visitor: NodeVisitor<T>): T
}