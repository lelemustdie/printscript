package org.example.parser

import org.example.token.Token
import org.example.token.TokenType

class TokenSearcher {
    companion object {
        fun searchForToken(
            tokens: List<Token>,
            tokenType: TokenType,
        ): Token {
            for (token in tokens) {
                if (token.type == tokenType) {
                    return token
                }
            }
            throw Exception("Token not found")
        }

        fun searchForToken(
            tokens: List<Token>,
            tokenTypes: List<TokenType>,
        ): Token {
            for (token in tokens) {
                if (tokenTypes.contains(token.type)) {
                    return token
                }
            }
            throw Exception("Token not found")
        }
    }
}
