package org.example.token

class Token {
    val type: TokenType
    val value: String
    val position: Int

    constructor(type: TokenType, value: String, position: Int) {
        this.type = type
        this.value = value
        this.position = position
    }

    override fun toString(): String {
        return "Token(type=$type, value='$value', position=$position)"
    }
}