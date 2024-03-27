import org.example.InterpreterImpl
import org.example.ast.nodes.ExpressionNode
import org.example.ast.nodes.ProgramNode
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
            Token(TokenType.LITERAL_NUMBER, "5.0", 10),
            Token(TokenType.SEMICOLON, ";", 12)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("5.0", interpreter.getVariables()["x"]?.value)
    }

    @Test
    fun testInterpreterWhenDeclaringAVariableAndItAlreadyExistsShouldThrowException() {

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

        val tokens2 = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.SEMICOLON, ";", 12)
        )

        val parser2 = ParserImpl(tokens2)
        val ast2 = parser2.parse()
        val interpreter2 = InterpreterImpl(ast2)

        try {
            interpreter2.interpret()
        } catch (e: Exception) {
            assertEquals("Variable x already exists", e.message)
        }
    }

    @Test
    fun testInterpreterWhenPrintingAVariableThatDoesNotExistsShouldThrowException() {


        val tokens2 = listOf(
            Token(TokenType.OPERATOR_PRINTLN, "println", 0),
            Token(TokenType.PARENTHESIS_OPEN, "(", 1),
            Token(TokenType.IDENTIFIER, "y", 2),
            Token(TokenType.PARENTHESIS_CLOSE, ")", 3),
            Token(TokenType.SEMICOLON, ";", 4)
        )

        val parser2 = ParserImpl(tokens2)
        val ast2 = parser2.parse()
        val interpreter2 = InterpreterImpl(ast2)

        try {
            interpreter2.interpret()
        } catch (e: Exception) {
            assertEquals("Variable y not found", e.message)
        }
    }
    @Test
    fun testInterpreterWhenRecievingAnInvalidNodeShouldThrowException(){
        val ast = ProgramNode(listOf(ExpressionNode.IdentifierNode(Token(TokenType.IDENTIFIER, "x", 0))))
        val interpreter = InterpreterImpl(ast)

        try {
            interpreter.interpret()
        } catch (e: Exception) {
            assertEquals("Unknown node type", e.message)
        }
    }

    @Test
    fun testInterpreterWhenRecievingADeclarationWithABinaryOperationShouldSaveTheResult(){
        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.OPERATOR_PLUS, "+", 12),
            Token(TokenType.LITERAL_NUMBER, "5", 14),
            Token(TokenType.SEMICOLON, ";", 16)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("10.0", interpreter.getVariables()["x"]?.value)
    }

    @Test
    fun testInterpreterWhenRecievingADeclarationWithAnIdentifierShouldSaveTheResult(){
        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5.0", 10),
            Token(TokenType.SEMICOLON, ";", 12),
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "y", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.IDENTIFIER, "x", 10),
            Token(TokenType.SEMICOLON, ";", 12)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("5.0", interpreter.getVariables()["y"]?.value)
    }

    @Test
    fun testInterpreterWhenAssignationAVariableToAnotherVariableShouldSaveItCorrectly(){
        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5.0", 10),
            Token(TokenType.SEMICOLON, ";", 12),
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "y", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "4.0", 10),
            Token(TokenType.SEMICOLON, ";", 12),
            Token(TokenType.IDENTIFIER, "y", 2),
            Token(TokenType.ASSIGNATOR, "=", 4),
            Token(TokenType.IDENTIFIER, "x", 6),

        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("5.0", interpreter.getVariables()["y"]?.value)
    }

    @Test
    fun testInterpreterWhenRecievingADeclarationWithABinaryOperationMinusShouldSaveTheResult(){
        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.OPERATOR_MINUS, "-", 12),
            Token(TokenType.LITERAL_NUMBER, "5", 14),
            Token(TokenType.SEMICOLON, ";", 16)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("0.0", interpreter.getVariables()["x"]?.value)
    }

    @Test
    fun testInterpreterWhenRecievingADeclarationWithABinaryOperationMultiplyShouldSaveTheResult(){
        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.OPERATOR_MULTIPLY, "*", 12),
            Token(TokenType.LITERAL_NUMBER, "5", 14),
            Token(TokenType.SEMICOLON, ";", 16)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("25.0", interpreter.getVariables()["x"]?.value)
    }

    @Test
    fun testInterpreterWhenRecievingADeclarationWithABinaryOperationDivideShouldSaveTheResult(){
        val tokens = listOf(
            Token(TokenType.KEYWORD_LET, "let", 0),
            Token(TokenType.IDENTIFIER, "x", 2),
            Token(TokenType.COLON, ":", 4),
            Token(TokenType.TYPE_NUMBER, "number", 21),
            Token(TokenType.ASSIGNATOR, "=", 8),
            Token(TokenType.LITERAL_NUMBER, "5", 10),
            Token(TokenType.OPERATOR_DIVIDE, "/", 12),
            Token(TokenType.LITERAL_NUMBER, "5", 14),
            Token(TokenType.SEMICOLON, ";", 16)
        )

        val parser = ParserImpl(tokens)
        val ast = parser.parse()
        val interpreter = InterpreterImpl(ast)
        interpreter.interpret()

        assertEquals("1.0", interpreter.getVariables()["x"]?.value)
    }
}