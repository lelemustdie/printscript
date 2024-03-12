package org.example.token

class Token(private val type: TokenType, private val value: String, private val position: Int) {

    override fun toString(): String {
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