package org.example

import org.example.ast.nodes.*
import org.example.factories.Literal


class InterpreterImpl(private val ast : ProgramNode) : Interpreter{

    private val variables = mutableMapOf<String, Literal>()
    override fun interpret() {
        val statements = ast.getStatements()
        for (statement in statements) {
            when (statement) {
                is PrintNode -> interpretPrintNode(statement)
                is StatementNode.AssignationNode -> interpretAssignationNode(statement)
                is ExpressionNode -> interpretExpressionNode(statement)
                else -> throw Exception("Unknown node type")
            }
        }
    }

    private fun interpretPrintNode(node: PrintNode) {
        val printable = node.getPrintable()
        println(evaluatePrintable(printable))
    }

    private fun evaluatePrintable(node : Node){
        when(node){
            is ExpressionNode.BinaryOperationNode -> {}
            is ExpressionNode.LiteralNode -> {printLiteral(node)}
            is ExpressionNode.IdentifierNode -> {printValueOfId(node)}
            else -> throw Exception("Unknown node type")
        }
    }

    private fun printLiteral(node : ExpressionNode.LiteralNode){
        println(node.getToken().getValue())
    }

    private fun printValueOfId(node : ExpressionNode.IdentifierNode){
        val id = node.getId().getValue()
        if(variables.containsKey(id)){
            println(variables.getValue(id).getValue())
        }else{
            throw Exception("Variable $id not found")
        }
    }

    private fun interpretAssignationNode(node: StatementNode.AssignationNode){
        val id = node.getId().getIdentifier().getId().getValue()
        val type = node.getId().getDataType().getTypeToken().getType()
        if (variables.containsKey(id)){
            if (variables.getValue(id).getType() == type) {
                variables.setValue(id, evaluateExpressionNode(node.getExpression()))
            }
            else{
                throw Exception("Type mismatch")
            }
        }
        else{
            variables[id] = evaluateExpressionNode(node.getExpression())
        }
    }

    private fun evaluateExpressionNode(){}
}