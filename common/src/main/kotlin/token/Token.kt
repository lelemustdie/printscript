package org.example.token

import kotlinx.serialization.Serializable

@Serializable
data class Token(private val type: TokenType, private val value: String, private val position: Int) {

    constructor(type: TokenType, position: Int) : this(type, "", position)

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