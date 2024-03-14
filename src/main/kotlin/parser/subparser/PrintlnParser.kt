package org.example.parser.subparser

import org.example.ast.Node
import org.example.ast.nodes.PrintNode
import org.example.parser.Parser
import org.example.parser.Subparsers.OperationCropper
import org.example.token.Token
import org.example.token.TokenType

class PrintlnParser(private val tokens: List<Token>): Parser{
    override fun parse(): Node {
        val valueNode = OperationParser.createValueNode(OperationCropper.crop(tokens,TokenType.OPERATOR_PRINTLN).listIterator())
            ?: throw Exception("Expected value after assignment operator")
        return PrintNode(valueNode)
    }
}