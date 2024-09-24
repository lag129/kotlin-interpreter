package lexer

import org.example.lexer.Lexer
import org.example.token.Token
import org.example.token.TokenType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LexerTest {

    private val input = "=+(){},;"

    private val tests = listOf(
        Token(TokenType.ASSIGN, "="),
        Token(TokenType.PLUS, "+"),
        Token(TokenType.LPAREN, "("),
        Token(TokenType.RPAREN, ")"),
        Token(TokenType.LBRACE, "{"),
        Token(TokenType.RBRACE, "}"),
        Token(TokenType.COMMA, ","),
        Token(TokenType.SEMICOLON, ";"),
        Token(TokenType.EOF, ""),
    )

    @Test
    fun testNextToken() {
        val lexer = Lexer(input)

        tests.forEach { expectedToken ->
            val token = lexer.nextToken()
            assertEquals(
                expectedToken.type,
                token.type,
                "期待されたTokenのTypeが異なります"
            )
            assertEquals(
                expectedToken.literal,
                token.literal,
                "期待されたTokenのLiteralが異なります"
            )
        }
    }
}