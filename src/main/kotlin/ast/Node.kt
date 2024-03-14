package org.example.ast

interface Node {
    fun <T> accept(visitor: NodeVisitor<T>): T
}