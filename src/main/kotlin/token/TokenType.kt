package org.example.token

enum class TokenType {
    KEYWORD_LET,
    IDENTIFIER,
    ASSIGNATOR,  // =
    COLON,
    SEMICOLON,
    LITERAL_NUMBER,
    LITERAL_STRING,
    TYPE_STRING,
    TYPE_NUMBER,
    OPERATOR_PLUS,
    OPERATOR_MINUS,
    OPERATOR_MULTIPLY, //priority 1
    OPERATOR_DIVIDE,    //priority 1
    OPERATOR_PRINTLN,   //priority last
}