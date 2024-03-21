import org.example.lexer.Lexer
import org.example.lexer.LexerImpl
import org.example.lexer.stringDivider.StringDividerImpl
import org.example.lexer.tokenMatchers.TokenMatcherImpl
import org.example.token.Token
import org.example.token.TokenType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class LexerTest {

    private lateinit var lexer: Lexer

    @BeforeEach
    fun setUp() {
        val strDiv = StringDividerImpl()
        val tokenMatcher = TokenMatcherImpl()
        lexer = LexerImpl(tokenMatcher, strDiv)
    }

    @Test
    fun testLexerWhenGivenCorrectInputShouldGiveCorrectTokens() {
        val input = "let x : number = 5;"

        val tokens = lexer.tokenize(input)

        val expectedTokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.SEMICOLON, ";", 12)
        )
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun testLexerWhenGivenIncorrectOutputShouldReturnIllegalArgumentException() {
        val input = " @ "

        try {
            lexer.tokenize(input)
        } catch (e: IllegalArgumentException) {
            assertEquals("No matching token for input: @, in line: 0", e.message)
        }
    }
}