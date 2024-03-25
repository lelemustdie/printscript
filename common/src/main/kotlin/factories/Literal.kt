package org.example.factories

import org.example.token.TokenType

class Literal(private val value: String, private val type: TokenType) {
    fun getValue(): String {
        return value
    }

    fun getType(): TokenType {
        return type
    }

}
