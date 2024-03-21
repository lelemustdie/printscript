package org.example.parser

import org.example.ast.nodes.Node

interface Parser {
    fun parse(): Node

}