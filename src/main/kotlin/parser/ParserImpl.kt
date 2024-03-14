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
            TokenType.KEYWORD_LET -> startAssignationStatement()              //skip Node
            TokenType.SEMICOLON -> endStatement()                             //skip Node
            TokenType.OPERATOR_PRINTLN -> startPrintStatement()               //skip Node

            TokenType.ASSIGNATOR -> TODO()                                    //assignation Node

//            TokenType.IDENTIFIER -> TODO()                                    //variable Node
//            TokenType.TYPE_STRING, TokenType.TYPE_NUMBER -> TODO()            //variable Node
//            TokenType.COLON -> TODO()                                         //skip/variable Node
//
//            TokenType.LITERAL_NUMBER, TokenType.LITERAL_STRING -> TODO()      //value Node
//            TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS -> TODO()       //value Node
//            TokenType.OPERATOR_MULTIPLY, TokenType.OPERATOR_DIVIDE -> TODO()  //value Node

        }
    }

    private fun getCurrentToken(tokens :List<Token>, current : Int): Token {
        return tokens[0 + current]
    }

    private fun startAssignationStatement(): AstNode {
        val asignatorToken = searchForAssignator()
        return AstNode(asignatorToken, createVariableNode(), createValueNode())
    }

    private fun createValueNode(): AstNode {
        return AstNode()
    }//cantidad de operadores = cantidad de nodos

    private fun createVariableNode(): AstNode {
        return AstNode(searchForColon(), AstNode(searchForIdentifier(),null,null), AstNode(searchForType(),null,null))
    }

    private fun searchForAssignator(): Token{
        val token = getCurrentToken(tokens, currentToken)
        if (token.getType() == TokenType.ASSIGNATOR) {
            return token
        }
        currentToken++
        return searchForAssignator()
    }

    private fun searchForColon(): Token{
        val token = getCurrentToken(tokens, currentToken)
        if (token.getType() == TokenType.COLON) {
            return token
        }
        currentToken++
        return searchForColon()
    }

    private fun searchForIdentifier(): Token{
        val token = getCurrentToken(tokens, currentToken)
        if (token.getType() == TokenType.IDENTIFIER) {
            return token
        }
        currentToken++
        return searchForIdentifier()
    }

    private fun searchForType(): Token{
        val token = getCurrentToken(tokens, currentToken)
        if (token.getType() == TokenType.TYPE_STRING || token.getType() == TokenType.TYPE_NUMBER) {
            return token
        }
        currentToken++
        return searchForType()
    }
}