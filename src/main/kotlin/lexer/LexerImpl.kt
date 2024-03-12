package org.example.lexer

import factories.TokenFactory
import org.example.lexer.stringDivider.StringDivider
import lexer.tokenMatchers.TokenMatcher
import org.example.token.Token

class LexerImpl(private val tokenMatcher: TokenMatcher, private val stringDivider: StringDivider) : Lexer{

    override fun tokenize(input: String): List<Token> {
        val lines = stringDivider.stringToList(input)
        val tokens = mutableListOf<Token>()
        var pos = 0
        for (line in lines) {
            for (word in line) {
                val token = TokenFactory.createValueToken(tokenMatcher.getToken(word, pos), word, pos)
                tokens.add(token)
                pos += word.length + 1
            }
        }
        return tokens
    }

}