package org.example.parser

import org.example.ast.Node
import org.example.ast.nodes.*
import org.example.token.Token
import org.example.token.TokenType

class ParserImpl (private val tokens: List<Token>) : Parser{
    var index = 0
    override fun parse(): Node {
        val statements = mutableListOf<Node>()
        while (index < tokens.size) {
            val statement = parseStatement(tokens, index)
            statements.add(statement)
            index++
        }
        return ProgramNode(statements)
    }
    private fun parseStatement(tokens: List<Token>,index : Int): Node {
        val token = getCurrentToken(tokens, index)
        return when (token.getType()) {
            TokenType.KEYWORD_LET -> startAssignationStatement()              //skip Node
            TokenType.SEMICOLON -> endStatement()                             //skip Node
//            TokenType.OPERATOR_PRINTLN -> startPrintStatement()               //skip Node

            TokenType.IDENTIFIER -> startReasignationStatement()                //identifier Node
            else -> throw Exception("Invalid statement")
        }
    }

    private fun startReasignationStatement(): Node {
        val idNode = IdentifierNode(searchForToken(listOf(TokenType.IDENTIFIER)))
        val valueNode = createValueNode()
        return DeclarationNode(idNode, valueNode)
    }
    private fun getCurrentToken(tokens :List<Token>, current : Int): Token {
        return tokens[0 + current]
    }

    private fun startAssignationStatement(): Node {
        index = searchForToken(listOf(TokenType.SEMICOLON)).getPosition()
        return DeclarationNode(createVariableNode(), createValueNode())
    }

    private fun createValueNode(): Node {
        try {
            val opPlusMinus = searchForToken(listOf(TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS))
            val left = createValueNode()
            val right = createValueNode()
            return BinaryOperationNode(opPlusMinus, left, right)
        } catch (e: Exception) {
            try {
                val opMulDiv = searchForToken(listOf(TokenType.OPERATOR_MULTIPLY, TokenType.OPERATOR_DIVIDE))
                val left = createValueNode()
                val right = createValueNode()
                return BinaryOperationNode(opMulDiv, left, right)
            } catch (e: Exception) {
                val literalToken = searchForToken(listOf(TokenType.LITERAL_NUMBER, TokenType.LITERAL_STRING))
                return LiteralNode(literalToken)
            }
        }
    }

    private fun createVariableNode(): Node {
        val idNode = IdentifierNode(searchForToken(listOf(TokenType.IDENTIFIER)))
        val typeNode = TypeNode(searchForToken(listOf(TokenType.TYPE_STRING, TokenType.TYPE_NUMBER)))
        return DeclarationNode(idNode, typeNode)
    }

    private fun searchForToken(types: List<TokenType>): Token{
        for (token in tokens) {
            if (token.getType()==TokenType.SEMICOLON){
                throw Exception("No matching token found")
            }
            if (types.contains(token.getType())) {
                return token
            }
        }
        throw Exception("No matching token found")
    }

    private fun endStatement(): Node {
        return EndStatementNode()
    }

//    private fun startPrintStatement(): Node {
//
//    }
}