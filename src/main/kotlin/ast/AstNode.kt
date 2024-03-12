package org.example.ast
import org.example.token.Token


class AstNode (private val value: Token, private val right: AstNode?, private val left: AstNode?) {

    val tokenNode = value;
    val rightChild = right;
    val leftChild = left;
}