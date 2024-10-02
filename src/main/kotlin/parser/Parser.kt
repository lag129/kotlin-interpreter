package org.example.parser

import org.example.ast.*
import org.example.lexer.Lexer
import org.example.token.Token
import org.example.token.TokenType

class Parser(private val lexer: Lexer) {
    private var curToken: Token = lexer.nextToken()
    private var peekToken: Token = lexer.nextToken()

    private fun nextToken() {
        curToken = peekToken
        peekToken = lexer.nextToken()
    }

    fun parseProgram(): Program {
        val statements = mutableListOf<Statement>()

        while (curToken.type != TokenType.EOF) {
            val stmt = parseStatement()
            if (stmt != null) {
                statements.add(stmt)
            }
            nextToken()
        }

        return Program(statements)
    }

    private fun parseStatement(): LetStatement? {
        return when (curToken.type) {
            TokenType.LET -> parseLetStatement()
            else -> null
        }
    }

    private fun parseLetStatement(): LetStatement? {
        val stmt = LetStatement(curToken)

        if (!expectPeek(TokenType.IDENT)) {
            return null
        }

        stmt.name = Identifier(curToken, curToken.literal)

        if (!expectPeek(TokenType.ASSIGN)) {
            return null
        }

        while (!curTokenIs(TokenType.SEMICOLON)) {
            nextToken()
        }

        return stmt
    }

    private fun curTokenIs(@Suppress("SameParameterValue") t: TokenType): Boolean {
        return curToken.type == t
    }

    private fun peekTokenIs(t: TokenType): Boolean {
        return peekToken.type == t
    }

    private fun expectPeek(t: TokenType): Boolean {
        if (peekTokenIs(t)) {
            nextToken()
            return true
        } else {
            return false
        }
    }
}
