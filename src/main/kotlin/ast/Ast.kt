package org.example.ast

import org.example.token.Token

interface Node {
    fun tokenLiteral(): String
    fun string(): String
}

interface Statement : Node {
    fun statementNode()
}

interface Expression : Node {
    fun expressionNode()
}

data class Program(
    val statements: List<Statement>
) {
    fun tokenLiteral(): String {
        return if (statements.isNotEmpty()) {
            statements[0].tokenLiteral()
        } else {
            ""
        }
    }
}

data class LetStatement(
    val token: Token,
    val name: Identifier,
    val value: Expression,
)

fun LetStatement.statementNode() {

}

fun LetStatement.tokenLiteral(): String {
    return token.literal
}

data class Identifier(
    val token: Token,
    val value: String,
)

fun Identifier.expressionNode() {

}

fun Identifier.tokenLiteral(): String {
    return token.literal
}

