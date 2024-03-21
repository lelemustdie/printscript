package org.example.lexer

import org.example.token.Token

interface Lexer {
    fun tokenize(input: String): List<Token>

}