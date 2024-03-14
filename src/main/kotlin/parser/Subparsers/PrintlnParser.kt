package org.example.parser.Subparsers

import org.example.ast.Node
import org.example.ast.nodes.PrintNode
import org.example.parser.Parser
import org.example.token.Token

class PrintlnParser(private val tokens: List<Token>): Parser{
    override fun parse(): Node {
        val valueNode = OperationParser.createValueNode(tokens.listIterator())
        return PrintNode(valueNode)
    }
}