package org.example.lexer.tokenMatcher

import org.example.token.Token

interface TokenMatcher {
    fun getToken(input: String, pos: Int): Token
}
