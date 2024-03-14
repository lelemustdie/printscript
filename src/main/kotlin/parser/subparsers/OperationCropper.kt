package org.example.parser.subparsers

import org.example.token.Token
import org.example.token.TokenType

class OperationCropper() {
    companion object {
        fun crop(tokens: List<Token>,type : TokenType): List<Token> {
            val newList = mutableListOf<Token>()
            var foundEquals = false

            for (token in tokens) {
                if (foundEquals) {
                    newList.add(token)
                } else if (token.getType() == type) {
                    foundEquals = true
                }
            }
            if (!foundEquals) {
                return emptyList()
            }
            return newList
        }
    }
}