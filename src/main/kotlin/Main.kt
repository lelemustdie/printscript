package org.example

import FormatterImpl
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.lexer.LexerImpl
import org.example.lexer.stringDivider.StringDividerImpl
import org.example.parser.ParserImpl

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val text = "let num:number = 2+5+7* 4 + 3 - 8 ;num = 5 "
    // \\
    val strDiv = StringDividerImpl()
    val lexer = LexerImpl()

    val dividedString = strDiv.stringToList(text)

    println("Divided string: $dividedString")

    val tokens = lexer.tokenize(text)


    println("Tokens: $tokens")

    val parser = ParserImpl(tokens)
    val ast = parser.parse()
    val astJson = Json.encodeToString(ast)
    println("AST JSON: $astJson")

    println("AST: $ast")

//    val interpreter = InterpreterImpl(ast)
//    interpreter.interpret()
//    println(interpreter.getVariables())
//
    println(FormatterImpl().format(ast))
}
