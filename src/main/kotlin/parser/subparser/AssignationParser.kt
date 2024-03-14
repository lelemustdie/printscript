package org.example.parser.Subparsers

import org.example.ast.Node
import org.example.ast.nodes.DeclarationNode
import org.example.ast.nodes.IdentifierNode
import org.example.ast.nodes.TypeNode
import org.example.parser.Parser
import org.example.parser.Subparsers.OperationCropper
import org.example.parser.TokenSearcher
import org.example.token.Token
import org.example.token.TokenType

class AssignationParser (private val tokens: List<Token>): Parser {

    override fun parse(): Node {
        val valueNode = OperationParser.createValueNode(OperationCropper.crop(tokens,TokenType.ASSIGNATOR).listIterator())
            ?: throw Exception("Expected value after assignment operator")
        return DeclarationNode(createVariableNode(), valueNode)
    }


    private fun createVariableNode(): Node {
        val idNode = IdentifierNode(TokenSearcher.searchForToken(tokens, listOf(TokenType.IDENTIFIER)))
        val typeNode = TypeNode(TokenSearcher.searchForToken(tokens, listOf(TokenType.TYPE_STRING, TokenType.TYPE_NUMBER)))
        return DeclarationNode(idNode, typeNode)
    }
}