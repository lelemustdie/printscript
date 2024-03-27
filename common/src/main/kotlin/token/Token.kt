package org.example.token

import kotlinx.serialization.Serializable

@Serializable
data class Token(val type: TokenType, val value: String, val position: Int) {



}