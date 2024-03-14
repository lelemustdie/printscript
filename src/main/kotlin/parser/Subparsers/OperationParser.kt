package org.example.parser.Subparsers

import org.example.ast.Node
import org.example.ast.nodes.BinaryOperationNode
import org.example.ast.nodes.LiteralNode
import org.example.token.Token
import org.example.token.TokenType

class OperationParser {
    companion object {
        fun createValueNode(iterator: ListIterator<Token>): Node {
            if (!iterator.hasNext()) error("Unexpected end of input")
            val token = iterator.next()
            return when (token.getType()) {
                TokenType.LITERAL_NUMBER, TokenType.LITERAL_STRING -> LiteralNode(token)
                TokenType.PARENTHESIS_OPEN -> {
                    val node = createExpressionNode(iterator) ?: error("Expected expression inside brackets")
                    if (!iterator.hasNext() || iterator.next().getType() != TokenType.PARENTHESIS_CLOSE) {
                        error("Expected closing bracket")
                    }
                    node
                }
                else -> {
                    iterator.previous()
                    val opPlusMinus = searchForToken(iterator, listOf(TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS))
                    if (opPlusMinus != null) {
                        val left = createValueNode(iterator) ?: error("Expected left operand after operator")
                        val right = createValueNode(iterator) ?: error("Expected right operand after operator")
                        BinaryOperationNode(opPlusMinus, left, right)
                    } else {
                        val opMulDiv = searchForToken(iterator, listOf(TokenType.OPERATOR_MULTIPLY, TokenType.OPERATOR_DIVIDE))
                        if (opMulDiv != null) {
                            val left = createValueNode(iterator) ?: error("Expected left operand after operator")
                            val right = createValueNode(iterator) ?: error("Expected right operand after operator")
                            BinaryOperationNode(opMulDiv, left, right)
                        } else {
                            error("Unexpected token: $token")
                        }
                    }
                }
            }
        }

        private fun createExpressionNode(iterator: ListIterator<Token>): Node {
            var node = createValueNode(iterator)
            while (iterator.hasNext()) {
                val token = iterator.next()
                if (token.getType() in listOf(TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS)) {
                    val rightNode = createValueNode(iterator) ?: error("Expected term after operator")
                    node = BinaryOperationNode(token, node, rightNode)
                } else {
                    iterator.previous()
                    break
                }
            }
            return node
        }

        private fun searchForToken(iterator: ListIterator<Token>, tokenTypes: List<TokenType>): Token? {
            while (iterator.hasNext()) {
                val token = iterator.next()
                if (token.getType() in tokenTypes) {
                    return token
                }
            }
            return null
        }
    }
}