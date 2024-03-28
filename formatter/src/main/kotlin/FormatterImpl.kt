import org.example.ast.nodes.ProgramNode
import java.io.File
import com.google.gson.Gson
import org.example.ast.nodes.ExpressionNode
import org.example.ast.nodes.StatementNode
import org.example.token.TokenType

class FormatterImpl: Formatter{
    override fun format(ast : ProgramNode): String {
        val jsonFile = File("formatter/src/main/resources/rules.json")
        val json = jsonFile.readText()

        val gson = Gson()
        val rulesWrapper: FormattingRulesWrapper = gson.fromJson(json, FormattingRulesWrapper::class.java)
        val rules = rulesWrapper.rules

        return evaluate(ast, rules)
    }

    private fun evaluate(ast: ProgramNode, rules: FormattingRules): String {
        var string = ""
        for (statement in ast.getStatements()) {
            when(statement){
              is StatementNode.PrintNode -> {string += evaluatePrintNode(statement, rules)}
                is StatementNode.DeclarationNode -> {string += evaluateDeclarationNode(statement, rules)}
                is StatementNode.AssignationNode -> {string += evaluateAssignationNode(statement, rules)}
                else -> throw Exception("Unknown node type")
            }
        }
        return string
    }

    private fun evaluatePrintNode(node: StatementNode.PrintNode, rules: FormattingRules): String {
        var string = ""
        if (rules.numberNewLinesBeforePrint > 0) {
            string += "\n".repeat(rules.numberNewLinesBeforePrint)
        }
        string += "println("
        string += evaluateExpressionNode(node.printable, rules)
        string += ");"
        string += "\n"
        return string
    }

    private fun evaluateDeclarationNode(node: StatementNode.DeclarationNode, rules: FormattingRules): String {
        //let:number = 5;
        var string = "let "
        val id = node.variable.identifier.id.value
        string += id
        if (rules.numberSpacesBeforeColon > 0) {
            string += " ".repeat(rules.numberSpaceAfterColon)
        }
        string += ":"
        if (rules.numberSpaceAfterColon > 0) {
            string += " ".repeat(rules.numberSpaceAfterColon)
        }
        string += type(node.variable.dataType.typeToken.type)
        if (rules.numberSpaceBeforeAssignation > 0) {
            string += " ".repeat(rules.numberSpaceBeforeAssignation)
        }
        string += "="
        if (rules.numberSpaceAfterAssignation > 0) {
            string += " ".repeat(rules.numberSpaceAfterAssignation)
        }
        string += evaluateExpressionNode(node.expression, rules)
        string += ";"
        string += "\n"
        return string
    }

    private fun evaluateAssignationNode(node: StatementNode.AssignationNode, rules: FormattingRules): String {
        //nombre = 5;
        var string = node.identifier.id.value
        if (rules.numberSpaceBeforeAssignation > 0) {
            string += " ".repeat(rules.numberSpaceBeforeAssignation)
        }
        string += "="
        if (rules.numberSpaceAfterAssignation > 0) {
            string += " ".repeat(rules.numberSpaceAfterAssignation)
        }
        string += evaluateExpressionNode(node.expression, rules)
        string += ";"
        string += "\n"
        return string
    }

    private fun evaluateExpressionNode(node: ExpressionNode, rules: FormattingRules): String {
        return when(node){
            is ExpressionNode.BinaryOperationNode -> {
                evaluateBinaryOperationNode(node, rules)
            }

            is ExpressionNode.LiteralNode -> {
                 node.token.value.toDouble().toInt().toString()
            }

            is ExpressionNode.IdentifierNode -> {
                node.id.value
            }

            is ExpressionNode.TypeNode -> {
                node.typeToken.value
            }

            else -> throw Exception("Unknown node type")
        }
    }

    private fun evaluateBinaryOperationNode(node: ExpressionNode.BinaryOperationNode, rules: FormattingRules): String {
        val left = evaluateExpressionNode(node.leftChild,rules)
        val right = evaluateExpressionNode(node.rightChild,rules)
        val operation = node.value.value
        return "$left $operation $right"
    }

    private fun type(type:TokenType): String {
        return when(type){
            TokenType.TYPE_NUMBER -> "number"
            TokenType.TYPE_STRING -> "string"
            else -> throw Exception("Unknown type")
        }
    }
}