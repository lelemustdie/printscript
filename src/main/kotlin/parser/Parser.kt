package org.example.parser

import org.example.ast.AstNode

interface Parser {
    fun parse(): AstNode
}