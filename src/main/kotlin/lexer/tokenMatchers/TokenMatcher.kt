package lexer.tokenMatchers
import org.example.token.TokenType

interface TokenMatcher {
    fun getToken(input: String, pos: Int): TokenType
}
