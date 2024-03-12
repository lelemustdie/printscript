package org.example.lexer

import org.example.lexer.stringDivider.StringDivider
import org.example.lexer.tokenMatcher.TokenMatcher
import org.example.token.Token

class LexerImpl(private val tokenMatcher: TokenMatcher, private val stringDivider: StringDivider) : Lexer{

    public override fun tokenize(input: String): List<Token> {
        val lines = stringDivider.stringToList(input)
        val tokens = mutableListOf<Token>()
        for (line in lines) {
            for (word in line) {
                if (tokenMatcher.isValidToken(word)) {
                    tokens.add(tokenMatcher.getToken(word))
                }
            }
        }
        return tokens
    }

}