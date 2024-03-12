package org.example.parser

import org.example.ast.Ast
import org.example.ast.AstNode
import org.example.token.Token

interface Parser {
    fun buildAst () : Ast
    fun nextToken(input: List<Token>): Token
}