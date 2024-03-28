import org.example.ast.nodes.ProgramNode


interface Formatter {
    fun format(ast: ProgramNode): String
}