package factories
import org.example.token.Token
import org.example.token.TokenType

class TokenFactory {
    companion object {
        fun createValueToken(type: TokenType, value: String, position: Int): Token {
            return Token(type, value, position)
        }

        fun createToken(type: TokenType, position: Int): Token {
            return Token(type, position)
        }
    }
}