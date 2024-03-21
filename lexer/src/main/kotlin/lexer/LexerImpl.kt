package org.example.lexer

import org.example.factories.TokenFactory
import org.example.lexer.stringDivider.StringDividerImpl
import org.example.lexer.tokenMatchers.TokenMatcherImpl
import org.example.token.Token

class LexerImpl() : Lexer {
    private val tokenMatcher = TokenMatcherImpl()
    private val stringDivider = StringDividerImpl()

    override fun tokenize(input: String): List<Token> {
        return stringDivider.stringToList(input).flatMapIndexed { lineIndex, line ->
            line.mapIndexed { wordIndex, word ->
                val position = lineIndex * (line.size + 1) + wordIndex * (word.length + 1)
                TokenFactory.createValueToken(tokenMatcher.getToken(word, position), word, position)
            }
        }
    }



}