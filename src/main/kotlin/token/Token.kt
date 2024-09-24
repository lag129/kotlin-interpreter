package org.example.token

enum class TokenType(val literal: String) {
    ILLEGAL("ILLEGAL"),
    EOF("EOF"),

    // 識別子+リテラル
    IDENT("IDENT"),
    INT("INT"),

    // 演算子
    ASSIGN("="),
    PLUS("+"),
    MINUS("-"),
    BANG("!"),
    ASTERISK("*"),
    SLASH("/"),

    LT("<"),
    GT(">"),


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

var keywords = mapOf(
    "fn" to TokenType.FUNCTION,
    "let" to TokenType.LET
)

fun lookupIdent(ident: String): TokenType {
    return keywords[ident] ?: TokenType.IDENT
}