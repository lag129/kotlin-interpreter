package org.example.lexer

import org.example.token.Token
import org.example.token.TokenType
import org.example.token.lookupIdent

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

    private fun peekChar(): Char {
        return if (readPosition >= input.length) {
            '\u0000'
        } else {
            input[readPosition]
        }
    }

    fun nextToken(): Token {
        skipWhitespace()
        val tok: Token = when (ch) {
            '=' -> {
                if (peekChar() == '=') {
                    val currentChar = ch
                    readChar()
                    Token(TokenType.EQ, "$currentChar$ch")
                } else {
                    newToken(TokenType.ASSIGN, ch)
                }
            }

            ';' -> newToken(TokenType.SEMICOLON, ch)
            '(' -> newToken(TokenType.LPAREN, ch)
            ')' -> newToken(TokenType.RPAREN, ch)
            ',' -> newToken(TokenType.COMMA, ch)
            '+' -> newToken(TokenType.PLUS, ch)
            '-' -> newToken(TokenType.MINUS, ch)
            '!' -> {
                if (peekChar() == '=') {
                    val currentChar = ch
                    readChar()
                    Token(TokenType.NOT_EQ, "$currentChar$ch")
                } else {
                    newToken(TokenType.BANG, ch)
                }
            }

            '*' -> newToken(TokenType.ASTERISK, ch)
            '/' -> newToken(TokenType.SLASH, ch)
            '<' -> newToken(TokenType.LT, ch)
            '>' -> newToken(TokenType.GT, ch)
            '{' -> newToken(TokenType.LBRACE, ch)
            '}' -> newToken(TokenType.RBRACE, ch)
            '\u0000' -> Token(TokenType.EOF, "")
            else -> {
                if (isLetter(ch)) {
                    val literal = readIdentifier()
                    val type = lookupIdent(literal)
                    return Token(type, literal)
                } else if (isDigit(ch)) {
                    val type = TokenType.INT
                    val literal = readNumber()
                    return Token(type, literal)
                } else {
                    newToken(TokenType.ILLEGAL, ch)
                }
            }
        }

        readChar()
        return tok
    }

    private fun skipWhitespace() {
        while (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
            readChar()
        }
    }

    private fun readIdentifier(): String {
        val startPosition = position
        while (isLetter(ch)) {
            readChar()
        }
        return input.substring(startPosition, position)
    }

    private fun isLetter(ch: Char): Boolean {
        return ch in 'a'..'z' || ch in 'A'..'Z' || ch == '_'
    }

    private fun readNumber(): String {
        val startPosition = position
        while (isDigit(ch)) {
            readChar()
        }
        return input.substring(startPosition, position)
    }

    private fun isDigit(ch: Char): Boolean {
        return ch in '0'..'9'
    }

    private fun newToken(tokenType: TokenType, ch: Char): Token {
        return Token(tokenType, ch.toString())
    }
}