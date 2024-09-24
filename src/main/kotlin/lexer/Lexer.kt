package org.example.lexer

import org.example.token.Token
import org.example.token.TokenType

class Lexer(private val input: String) {
    private var position = 0
    private var readPosition = 0
    private var ch = '\u0000'

    init {
        readChar()
    }

    private fun readChar() {
        ch = if (readPosition >= input.length) {
            '\u0000'
        } else {
            input[readPosition]
        }
        position = readPosition
        readPosition++
    }

    fun nextToken(): Token {
        val tok: Token = when (ch) {
            '=' -> newToken(TokenType.ASSIGN, ch)
            ';' -> newToken(TokenType.SEMICOLON, ch)
            '(' -> newToken(TokenType.LPAREN, ch)
            ')' -> newToken(TokenType.RPAREN, ch)
            ',' -> newToken(TokenType.COMMA, ch)
            '+' -> newToken(TokenType.PLUS, ch)
            '{' -> newToken(TokenType.LBRACE, ch)
            '}' -> newToken(TokenType.RBRACE, ch)
            '\u0000' -> Token(TokenType.EOF, "")
            else -> Token(TokenType.ILLEGAL, ch.toString())
        }

        readChar()
        return tok
    }

    private fun newToken(tokenType: TokenType, ch: Char): Token {
        return Token(tokenType, ch.toString())
    }
}