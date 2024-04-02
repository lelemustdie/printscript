import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import org.example.InterpreterImpl
import org.example.lexer.LexerImpl
import org.example.parser.ParserImpl

class CodeRunner : CliktCommand() {
    private val inputFile by option("-i", "--input", help = "Input file").file(exists = true, readable = true)
    private val outputFile by option("-o", "--output", help = "Output file").file(writable = true)

    override fun run() {
        inputFile?.let { file ->
            // Read the input file
            val input = file.readText()

            // Process the input based on the command
            // For example:
            // - Pass the input through lexer
            // - Parse the tokens into AST
            // - Lint or format the AST
            // - Interpret or compile the code
            val lexer = LexerImpl()
            val tokens = lexer.tokenize(input)

            val parser = ParserImpl(tokens)
            val ast = parser.parse()

            val interpreter = InterpreterImpl(ast)
            interpreter.interpret()

            // Dummy output for demonstration
            val output = "Processed code"

            // Write the output to the output file if provided, otherwise print to console
            if (outputFile != null) {
                outputFile!!.writeText(output)
            } else {
                println(output)
            }
        } ?: throw IllegalArgumentException("Input file is required")
    }
}

fun main(args: Array<String>) = CodeRunner().main(args)
