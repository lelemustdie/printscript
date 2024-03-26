import org.example.InterpreterImpl
import org.example.parser.ParserImpl
import org.example.token.Token
import org.example.token.TokenType
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class InterpreterTest {

    @Test
    fun testInterpreterWhenDeclaringAVariableAndItDoesNotExistsShouldSaveIt() {

        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.SEMICOLON, ";", 12)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()
        assertEquals(5, interpreter.getVariables()["x"]?.value)
    }

}