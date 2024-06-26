package org.example.lexer.tokenMatchers
import org.example.token.TokenType

class TokenMatcherImpl : TokenMatcher {
    private val regexMap: Map<Regex, TokenType> =
        mapOf(
            Regex("let") to TokenType.KEYWORD_LET,
            Regex("string") to TokenType.TYPE_STRING,
            Regex("number") to TokenType.TYPE_NUMBER,
            Regex("println") to TokenType.OPERATOR_PRINTLN,
            Regex("[a-zA-Z][a-zA-Z0-9_]*") to TokenType.IDENTIFIER,
            Regex("=") to TokenType.ASSIGNATOR,
            Regex(":") to TokenType.COLON,
            Regex(";") to TokenType.SEMICOLON,
            Regex("[0-9]+") to TokenType.LITERAL_NUMBER,
            Regex("""(['"]).*?\1""") to TokenType.LITERAL_STRING,
            Regex("\\+") to TokenType.OPERATOR_PLUS,
            Regex("-") to TokenType.OPERATOR_MINUS,
            Regex("\\*") to TokenType.OPERATOR_MULTIPLY,
            Regex("/") to TokenType.OPERATOR_DIVIDE,
            Regex("\\(") to TokenType.PARENTHESIS_OPEN,
            Regex("\\)") to TokenType.PARENTHESIS_CLOSE,
        )

    override fun getToken(
        input: String,
        position: Int,
    ): TokenType {
        for ((regex, tokenType) in regexMap) {
            if (regex.matches(input)) {
                return tokenType
            }
        }
        throw IllegalArgumentException("No matching token for input: $input, in line: $position")
    }
}
