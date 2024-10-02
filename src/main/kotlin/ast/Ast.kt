package org.example.ast

import org.example.token.Token

interface Node {
    fun tokenLiteral(): String
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
    var token: Token,
    var name: Identifier? = null,
    var value: Expression? = null,
) : Statement {
    override fun statementNode() {}

    override fun tokenLiteral(): String {
        return token.literal
    }
}

data class ReturnStatement(
    var token: Token,
    var returnValue: Expression? = null,
) : Statement {
    override fun statementNode() {}

    override fun tokenLiteral(): String {
        return token.literal
    }
}

data class Identifier(
    val token: Token,
    val value: String,
) : Expression {
    override fun expressionNode() {}

    override fun tokenLiteral(): String {
        return token.literal
    }
}

