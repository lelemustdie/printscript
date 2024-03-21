package org.example

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.lexer.LexerImpl
import org.example.lexer.stringDivider.StringDividerImpl
import org.example.parser.ParserImpl

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val text = "let x: number = (2 * 5 + 2) / 5; t=(3 + 5); \n println(5); "
    // \\
    val strDiv = StringDividerImpl()
    val lexer = LexerImpl()

    val dividedString = strDiv.stringToList(text)
    val tokens = lexer.tokenize(text)

    println("Divided string: $dividedString")
    println("Tokens: $tokens")

    val parser = ParserImpl(tokens)
    val ast = parser.parse()
    val astJson = Json.encodeToString(ast)
    println("AST: $astJson")
}