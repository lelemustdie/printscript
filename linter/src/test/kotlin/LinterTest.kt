import linter.LinterImpl
import org.example.ast.nodes.ExpressionNode
import org.example.ast.nodes.ProgramNode
import org.example.ast.nodes.StatementNode
import org.example.token.Token
import org.example.token.TokenType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LinterTest {
    @Test
    fun testLinterWithNoErrors() {
        val ast =
            ProgramNode(
                listOf(
                    StatementNode.PrintNode(
                        ExpressionNode.LiteralNode(Token(TokenType.LITERAL_STRING, "hello", 0)),
                    ),
                    StatementNode.PrintNode(
                        ExpressionNode.LiteralNode(Token(TokenType.LITERAL_STRING, "world", 0)),
                    ),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assert(errors.isEmpty())
    }

    @Test
    fun testLinterWithOperationInPrintln() {
        val ast =
            ProgramNode(
                listOf(
                    StatementNode.PrintNode(
                        ExpressionNode.BinaryOperationNode(
                            Token(TokenType.OPERATOR_PLUS, "+", 0),
                            ExpressionNode.LiteralNode(Token(TokenType.LITERAL_NUMBER, "5", 0)),
                            ExpressionNode.LiteralNode(Token(TokenType.LITERAL_NUMBER, "3", 0)),
                        ),
                    ),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assertEquals(errors.size, 1)
        assertEquals(errors[0].message, "Binary operation in println")
    }

    @Test
    fun testLinterWithErrorInFormatIdDeclarationNode() {
        val ast =
            ProgramNode(
                listOf(
                    StatementNode.DeclarationNode(
                        StatementNode.VariableNode(
                            ExpressionNode.IdentifierNode(Token(TokenType.IDENTIFIER, "Helloworld", 0)),
                            ExpressionNode.TypeNode(Token(TokenType.TYPE_STRING, "String", 0)),
                        ),
                        ExpressionNode.LiteralNode(Token(TokenType.LITERAL_STRING, "world", 0)),
                    ),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assertEquals(errors.size, 1)
        assertEquals(errors[0].message, "Identifier Helloworld is not in camelCase format")
    }

    @Test
    fun testLinterWithNoErrorsInFormatId() {
        val ast =
            ProgramNode(
                listOf(
                    StatementNode.DeclarationNode(
                        StatementNode.VariableNode(
                            ExpressionNode.IdentifierNode(Token(TokenType.IDENTIFIER, "helloWorld", 0)),
                            ExpressionNode.TypeNode(Token(TokenType.TYPE_STRING, "String", 0)),
                        ),
                        ExpressionNode.LiteralNode(Token(TokenType.LITERAL_STRING, "world", 0)),
                    ),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assertEquals(errors.size, 0)
    }

    @Test
    fun testLinterWithErrorInFormatIdIdNode() {
        val ast =
            ProgramNode(
                listOf(
                    ExpressionNode.IdentifierNode(Token(TokenType.IDENTIFIER, "hello_world", 0)),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assertEquals(errors.size, 1)
        assertEquals(errors[0].message, "Identifier hello_world is not in camelCase format")
    }

    @Test
    fun testLinterWithErrorInFormatIdAssignationNode() {
        val ast =
            ProgramNode(
                listOf(
                    StatementNode.AssignationNode(
                        ExpressionNode.IdentifierNode(Token(TokenType.IDENTIFIER, "HelloWorld", 0)),
                        ExpressionNode.LiteralNode(Token(TokenType.LITERAL_STRING, "world", 0)),
                    ),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assertEquals(errors.size, 1)
        assertEquals(errors[0].message, "Identifier HelloWorld is not in camelCase format")
    }

    @Test
    fun testLinterWithErrorInFormatIdVariableNode() {
        val ast =
            ProgramNode(
                listOf(
                    StatementNode.VariableNode(
                        ExpressionNode.IdentifierNode(Token(TokenType.IDENTIFIER, "HelloWorld", 0)),
                        ExpressionNode.TypeNode(Token(TokenType.TYPE_STRING, "String", 0)),
                    ),
                ),
            )
        val linter = LinterImpl()
        val errors = linter.checkErrors(ast)
        assertEquals(errors.size, 1)
        assertEquals(errors[0].message, "Identifier HelloWorld is not in camelCase format")
    }
}
