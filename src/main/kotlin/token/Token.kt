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
    TRUE("TRUE"),
    FALSE("FALSE"),
    IF("IF"),
    ELSE("ELSE"),
    RETURN("RETURN"),

    EQ("=="),
    NOT_EQ("!=")
}

data class Token(
    val type: TokenType,
    val literal: String
)

var keywords = mapOf(
    "fn" to TokenType.FUNCTION,
    "let" to TokenType.LET,
    "true" to TokenType.TRUE,
    "false" to TokenType.FALSE,
    "if" to TokenType.IF,
    "else" to TokenType.ELSE,
    "return" to TokenType.RETURN
)

fun lookupIdent(ident: String): TokenType {
    return keywords[ident] ?: TokenType.IDENT
}