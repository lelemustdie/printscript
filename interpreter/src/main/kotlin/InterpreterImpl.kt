package org.example

import org.example.ast.nodes.*
import org.example.factories.Literal
import org.example.token.TokenType


class InterpreterImpl(private val ast : ProgramNode) : Interpreter{

    private val variables = mutableMapOf<String, Literal>()

    fun getVariables() : MutableMap<String, Literal>{
        return variables
    }

    override fun interpret() {
        val statements = ast.getStatements()
        for (statement in statements) {
            when (statement) {
                is StatementNode.PrintNode -> interpretPrintNode(statement)
                is StatementNode.DeclarationNode -> interpretDeclarationNode(statement)
                is StatementNode.AssignationNode -> interpretAssignationNode(statement)
                else -> throw Exception("Unknown node type")
            }
        }
    }

    private fun interpretPrintNode(node: StatementNode.PrintNode) {
        when(val printable = node.printable){
            is ExpressionNode.LiteralNode -> {printLiteral(printable)}
            is ExpressionNode.IdentifierNode -> {printValueOfId(printable)}
            else -> throw Exception("Unknown node type")
        }
    }

    private fun printLiteral(node : ExpressionNode.LiteralNode){
        print(node.token.value)
    }

    private fun printValueOfId(node : ExpressionNode.IdentifierNode){
        val id = node.id.value
        if(variables.containsKey(id)){
            println(variables.getValue(id).value)
        }else{
            throw Exception("Variable $id not found")
        }
    }

    private fun interpretDeclarationNode(node: StatementNode.DeclarationNode) {
        val id = node.variable.identifier.id.value
        val expression = getExpression(node.expression)
        if (variables.containsKey(id)) {
            throw Exception("Variable $id already exists")
        }
        variables[id] = expression
    }

    private fun getExpression(node: Node) : Literal{
        return when(node){
            is ExpressionNode.BinaryOperationNode -> { binaryExpression(node) }
            is ExpressionNode.LiteralNode -> { Literal(node.token.value, node.token.type) }
            is ExpressionNode.IdentifierNode -> { identifierExpression(node) }
            else -> throw Exception("Unknown node type")
        }
    }

    private fun identifierExpression(node: ExpressionNode.IdentifierNode) : Literal{
        val id = node.id.value
        if(variables.containsKey(id)){
            return Literal(variables.getValue(id).value, variables.getValue(id).type)
        }else{
            throw Exception("Variable $id not found")
        }
    }

    private fun binaryExpression(node: ExpressionNode.BinaryOperationNode): Literal{
        val left = getExpression(node.leftChild)
        val right = getExpression(node.rightChild)

        return when(node.value.type){
            TokenType.OPERATOR_PLUS -> {evaluateAddition(left, right)}
            TokenType.OPERATOR_MINUS -> {evaluateSubtraction(left, right)}
            TokenType.OPERATOR_MULTIPLY -> {evaluateMultiplication(left, right)}
            TokenType.OPERATOR_DIVIDE -> {evaluateDivision(left, right)}
            TokenType.LITERAL_NUMBER -> {return Literal((node.value.value),node.value.type)}
            else -> throw Exception("Unknown operator")
        }
    }

    private fun evaluateAddition(left: Literal, right: Literal): Literal{
        if (left.type == TokenType.LITERAL_NUMBER && right.type == TokenType.LITERAL_NUMBER){
            return Literal((left.value.toDouble() + right.value.toDouble()).toString(),TokenType.LITERAL_NUMBER)
        }
        return Literal((left.value + right.value),left.type)
    }

    private fun evaluateSubtraction(left: Literal, right: Literal): Literal{
        return Literal((left.value.toDouble() - right.value.toDouble()).toString(),left.type)
    }

    private fun evaluateMultiplication(left: Literal, right: Literal): Literal{
        return Literal((left.value.toDouble() * right.value.toDouble()).toString(),left.type)
    }

    private fun evaluateDivision(left: Literal, right: Literal): Literal{
        return Literal((left.value.toDouble() / right.value.toDouble()).toString(),left.type)
    }


    private fun interpretAssignationNode(node: StatementNode.AssignationNode){
        val id = node.identifier.id.value
        val expression = getExpression(node.expression)
        if(variables.containsKey(id)){
            if (variables.getValue(id).type != expression.type){
                throw Exception("Type mismatch")
            }
            variables[id] = expression
        }else{
            throw Exception("Variable $id not found")
        }
    }
}