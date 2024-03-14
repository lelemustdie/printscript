package org.example.parser

import org.example.ast.Node

interface Parser {
    fun parse(): Node

}