package org.example.parser

import org.example.ast.AstNode
import org.example.token.Token
import org.example.token.TokenType

class ParserImpl (private val tokens: List<Token>) : Parser{
    private var currentToken = 0
    private val statements = mutableListOf<AstNode>()
    override fun parse(): AstNode {
        while (currentToken < tokens.size) {
            val statement = parseStatement(tokens)
            statements.add(statement)
            currentToken++
        }
        return buildAst(statements)
    }
    private fun parseStatement(tokens: List<Token>): AstNode {
        val token = getCurrentToken(tokens, currentToken)
        return when (token.getType()) {
            TokenType.KEYWORD_LET -> startAssignationStatement() //skip token
            TokenType.IDENTIFIER -> TODO()
            TokenType.ASSIGNATOR -> TODO()
            TokenType.COLON -> TODO()
            TokenType.SEMICOLON -> endStatement() //skip token
            TokenType.LITERAL_NUMBER, TokenType.LITERAL_STRING -> TODO()
            TokenType.TYPE_STRING,TokenType.TYPE_NUMBER -> TODO()
            TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS -> TODO()
            TokenType.OPERATOR_MULTIPLY, TokenType.OPERATOR_DIVIDE -> TODO()
            TokenType.OPERATOR_PRINTLN -> startPrintStatement() // skip token
        }
    }

    private fun getCurrentToken(tokens :List<Token>, current : Int): Token {
        return tokens[0 + current]
    }

    private fun startAssignationStatement(): AstNode {
        val asignatorToken = searchForAssignator()
        return AstNode(asignatorToken, null, null)
    }

    private fun searchForAssignator(): Token{
        val token = getCurrentToken(tokens, currentToken)
        if (token.getType() == TokenType.ASSIGNATOR) {
            return token
        }
        currentToken++
        return searchForAssignator()
    }
}