package org.example.parser

import org.example.ast.Ast
import org.example.token.Token

class ParserImpl (tokens: List<Token>) : Parser{

    val mappedList = tokens.map(buildAst())
    override fun buildAst () : List {

        val node = nextToken()


    }

    override fun nextToken(input: List<Token>): Token {
        val token = input.first()
        return token
    }

}