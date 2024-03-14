package org.example.lexer

import factories.TokenFactory
import org.example.lexer.stringDivider.StringDivider
import lexer.tokenMatchers.TokenMatcher
import org.example.token.Token

class LexerImpl(private val tokenMatcher: TokenMatcher, private val stringDivider: StringDivider) : Lexer{

    override fun tokenize(input: String): List<Token> {
        return stringDivider.stringToList(input).flatMapIndexed { lineIndex, line ->
            line.mapIndexed { wordIndex, word ->
                val position = lineIndex * (line.size + 1) + wordIndex * (word.length + 1)
                TokenFactory.createValueToken(tokenMatcher.getToken(word, position), word, position)
            }
        }
    }



}