package org.example.token

enum class TokenType(val literal: String) {
    ILLEGAL("ILLEGAL"),
    EOF("EOF"),

    // 識別子+リテラル
    INDENT("INDENT"),
    INT("INT"),

    // 演算子
    ASSIGN("="),
    PLUS("+"),


    // デリミタ
    COMMA(","),
    SEMICOLON(";"),

    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),

    // キーワード
    FUNCTION("FUNCTION"),
    LET("LET"),
}

data class Token(
    val type: TokenType,
    val literal: String
)