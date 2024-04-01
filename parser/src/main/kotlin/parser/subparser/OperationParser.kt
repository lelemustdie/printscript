package org.example.parser.subparser

import org.example.ast.nodes.ExpressionNode
import org.example.ast.nodes.Node
import org.example.token.Token
import org.example.token.TokenType

class OperationParser {
    companion object {
        fun createValueNode(iterator: ListIterator<Token>): Node? {
            if (!iterator.hasNext()) return null

            // Try parsing complex expressions first
            val complexNode = createComplexExpressionNode(iterator)
            if (complexNode != null) return complexNode

            // If no complex expression found, try parsing literals
            val token = iterator.next()
            return when (token.type) {
                TokenType.LITERAL_NUMBER, TokenType.LITERAL_STRING -> ExpressionNode.LiteralNode(token)
                TokenType.IDENTIFIER -> ExpressionNode.IdentifierNode(token)
                else -> error("Unexpected token: $token")
            }
        }

        private fun createComplexExpressionNode(iterator: ListIterator<Token>): Node? {
            var node = createTermNode(iterator)
            while (iterator.hasNext()) {
                val token = iterator.next()
                if (token.type in listOf(TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS)) {
                    val rightNode = createTermNode(iterator) ?: error("Expected term after operator")
                    node = ExpressionNode.BinaryOperationNode(token, node!!, rightNode)
                } else {
                    iterator.previous()
                    break
                }
            }
            return node
        }

        private fun createTermNode(iterator: ListIterator<Token>): Node? {
            var node = createFactorNode(iterator)
            while (iterator.hasNext()) {
                val token = iterator.next()
                if (token.type in listOf(TokenType.OPERATOR_MULTIPLY, TokenType.OPERATOR_DIVIDE)) {
                    val rightNode = createFactorNode(iterator) ?: error("Expected factor after operator")
                    node = ExpressionNode.BinaryOperationNode(token, node!!, rightNode)
                } else {
                    iterator.previous()
                    break
                }
            }
            return node
        }

        private fun createFactorNode(iterator: ListIterator<Token>): Node? {
            if (!iterator.hasNext()) return null
            val token = iterator.next()
            return when (token.type) {
                TokenType.LITERAL_NUMBER, TokenType.LITERAL_STRING -> ExpressionNode.LiteralNode(token)
                TokenType.IDENTIFIER -> ExpressionNode.IdentifierNode(token)
                TokenType.PARENTHESIS_OPEN -> {
                    val node = createComplexExpressionNode(iterator) ?: error("Expected expression inside brackets")
                    if (!iterator.hasNext() || iterator.next().type != TokenType.PARENTHESIS_CLOSE) {
                        error("Expected closing bracket")
                    }
                    node
                }
                else -> error("Unexpected token: $token")
            }
        }
    }
}
