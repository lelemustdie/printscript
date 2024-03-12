package org.example.lexer

import org.example.lexer.stringDivider.StringDivider
import org.example.lexer.tokenMatcher.TokenMatcher
import org.example.token.Token

class LexerImpl(private val tokenMatcher: TokenMatcher, private val stringDivider: StringDivider) : Lexer{

    override fun tokenize(input: String): List<Token> {
        val lines = stringDivider.stringToList(input)
        val tokens = mutableListOf<Token>()
        var pos = 0
        for (line in lines) {
            for (word in line) {
                tokens.add(tokenMatcher.getToken(word, pos))
                pos += word.length + 1
            }
        }
        return tokens
    }

}