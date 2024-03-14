package org.example.parser.Subparsers

import org.example.token.Token
import org.example.token.TokenType

class OperationCropper() {
    companion object {
        fun crop(tokens: List<Token>): List<Token> {
            val newList = mutableListOf<Token>()
            var foundEquals = false

            for (token in tokens) {
                if (foundEquals) {
                    newList.add(token)
                } else if (token.getType() == TokenType.ASSIGNATOR) {
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