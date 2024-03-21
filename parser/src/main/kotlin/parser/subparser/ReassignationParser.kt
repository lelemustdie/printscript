package org.example.parser.subparser

import org.example.ast.nodes.Node
import org.example.ast.nodes.DeclarationNode
import org.example.ast.nodes.IdentifierNode
import org.example.parser.Parser
import org.example.parser.TokenSearcher
import org.example.parser.subparsers.OperationCropper
import org.example.token.Token
import org.example.token.TokenType

class ReassignationParser(private val tokens: List<Token>): Parser {
    override fun parse(): Node {
        val idNode = IdentifierNode(TokenSearcher.searchForToken(tokens, listOf(TokenType.IDENTIFIER)))
        val valueNode = OperationParser.createValueNode(
            OperationCropper.crop(tokens, TokenType.ASSIGNATOR).listIterator()
        )
            ?: throw Exception("Expected value after reassignment operator")
        return DeclarationNode(idNode, valueNode)
    }

}