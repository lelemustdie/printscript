package factories
import org.example.token.Token
import org.example.token.TokenType

class TokenFactory {
    companion object {
        fun createToken(type: TokenType, value: String, pos: Int): Token {
            return Token(type, value, pos)
        }

        fun createToken(type: TokenType, pos: Int): Token {
            return Token(type, pos)
        }
    }
}