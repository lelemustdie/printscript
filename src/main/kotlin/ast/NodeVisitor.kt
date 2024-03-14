package org.example.ast

import org.example.ast.nodes.*

interface NodeVisitor<T> {
    fun visit(declarationNode: DeclarationNode): T
    fun visit(assignationNode: AssignationNode): T
    fun visit(binaryOperationNode: BinaryOperationNode): T
    fun visit(literalNode: LiteralNode): T
    fun visit(identifierNode: IdentifierNode): T
    fun visit(programNode: ProgramNode): T
    fun visit(typeNode: TypeNode): T
    fun visit(endStatementNode: EndStatementNode): T
    fun visit(printNode: PrintNode): T
}