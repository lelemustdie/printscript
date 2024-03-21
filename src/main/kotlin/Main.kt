package org.example

import org.example.lexer.LexerImpl
import org.example.lexer.stringDivider.StringDividerImpl
import org.example.lexer.tokenMatchers.TokenMatcherImpl
import org.example.parser.ParserImpl

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val text = "let x: number = (2 * 5 + 2) / 5; t=(3 + 5); \n println(5); "
    // \\
    val strDiv = StringDividerImpl()
    val tokenMatcher = TokenMatcherImpl()
    val lexer = LexerImpl(tokenMatcher, strDiv)

    val dividedString = strDiv.stringToList(text)
    val tokens = lexer.tokenize(text)

    println("Divided string: $dividedString")
    println("Tokens: $tokens")

    val parser = ParserImpl(tokens)
    val ast = parser.parse()
    println("AST: $ast")
}