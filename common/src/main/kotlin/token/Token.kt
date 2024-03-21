package org.example.token

class Token(private val type: TokenType, private val value: String, private val position: Int) {

    constructor(type: TokenType, position: Int) : this(type, "", position)
    override fun toString(): String {
        if (value.isEmpty()) {
            return "Token(type=$type, position=$position)"
        }
        return "Token(type=$type, value='$value', position=$position)"
    }
    fun getType(): TokenType {
        return type
    }
    fun getValue(): String {
        return value
    }
    fun getPosition(): Int {
        return position
    }
}