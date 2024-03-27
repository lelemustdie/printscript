package org.example.factories
import org.example.token.Token
import org.example.token.TokenType

class TokenFactory {
    companion object {
        fun createValueToken(type: TokenType, value: String, position: Int): Token {
            if (type == TokenType.LITERAL_STRING) {
                return Token(type, value.substring(1, value.length - 1), position)
            }
            return Token(type, value, position)
        }
    }
}