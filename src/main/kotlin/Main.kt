package org.example

import org.example.lexer.LexerImpl
import org.example.lexer.stringDivider.StringDividerImpl
import org.example.lexer.tokenMatcher.TokenMatcherImpl

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val text = "let x: number = 5\nlet aAfia: string = 'hello: i am world; very much a test @ heyo';" +
            "\nlet sugon_deese : number = 1 * 2 + 3 / 4"

    val strDiv = StringDividerImpl()
    val tokenMatcher = TokenMatcherImpl()
    val lexer = LexerImpl(tokenMatcher, strDiv)

    val dividedString = strDiv.stringToList(text)
    val tokens = lexer.tokenize(text)

    println("Divided string: $dividedString")
    println("Tokens: $tokens")
}