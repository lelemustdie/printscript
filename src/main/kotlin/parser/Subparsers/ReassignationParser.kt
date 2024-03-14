package org.example.parser.Subparsers

import org.example.ast.Node
import org.example.ast.nodes.DeclarationNode
import org.example.ast.nodes.IdentifierNode
import org.example.parser.Parser
import org.example.parser.TokenSearcher
import org.example.token.Token
import org.example.token.TokenType

class ReassignationParser(private val tokens: List<Token>): Parser {
    override fun parse(): Node {
        val idNode = IdentifierNode(TokenSearcher.searchForToken(tokens, listOf(TokenType.IDENTIFIER)))
        val valueNode = OperationParser.createValueNode(OperationCropper.crop(tokens).listIterator())
            ?: throw Exception("Expected value after assignment operator")
        return DeclarationNode(idNode, valueNode)
    }

}