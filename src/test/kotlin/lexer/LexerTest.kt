package lexer

import org.example.lexer.Lexer
import org.example.token.Token
import org.example.token.TokenType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LexerTest {
    @Test
    fun testNextToken() {
        val input = "=+(){},;"
        val tests = listOf(
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

    @Test
    fun testNextToken2() {
        val input = """
        let five = 5;
        let ten = 10;
        
        let add = fn(x, y) {
            x + y;
        };
        
        let result = add(five, ten);
        """
        val tests = listOf(
            Token(TokenType.LET, "let"),
            Token(TokenType.IDENT, "five"),
            Token(TokenType.ASSIGN, "="),
            Token(TokenType.INT, "5"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.LET, "let"),
            Token(TokenType.IDENT, "ten"),
            Token(TokenType.ASSIGN, "="),
            Token(TokenType.INT, "10"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.LET, "let"),
            Token(TokenType.IDENT, "add"),
            Token(TokenType.ASSIGN, "="),
            Token(TokenType.FUNCTION, "fn"),
            Token(TokenType.LPAREN, "("),
            Token(TokenType.IDENT, "x"),
            Token(TokenType.COMMA, ","),
            Token(TokenType.IDENT, "y"),
            Token(TokenType.RPAREN, ")"),
            Token(TokenType.LBRACE, "{"),
            Token(TokenType.IDENT, "x"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.IDENT, "y"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.RBRACE, "}"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.LET, "let"),
            Token(TokenType.IDENT, "result"),
            Token(TokenType.ASSIGN, "="),
            Token(TokenType.IDENT, "add"),
            Token(TokenType.LPAREN, "("),
            Token(TokenType.IDENT, "five"),
            Token(TokenType.COMMA, ","),
            Token(TokenType.IDENT, "ten"),
            Token(TokenType.RPAREN, ")"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.EOF, ""),
        )

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

    @Test
    fun testNextToken3() {
        val input = """
        !-/*5;
        5 < 10 > 5;
        """
        val tests = listOf(
            Token(TokenType.BANG, "!"),
            Token(TokenType.MINUS, "-"),
            Token(TokenType.SLASH, "/"),
            Token(TokenType.ASTERISK, "*"),
            Token(TokenType.INT, "5"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.INT, "5"),
            Token(TokenType.LT, "<"),
            Token(TokenType.INT, "10"),
            Token(TokenType.GT, ">"),
            Token(TokenType.INT, "5"),
        )

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

    @Test
    fun testNextToken4() {
        val input = """
        if (5 < 10) {
            return true;
        } else {
            return false;
        };
        """
        val tests = listOf(
            Token(TokenType.IF, "if"),
            Token(TokenType.LPAREN, "("),
            Token(TokenType.INT, "5"),
            Token(TokenType.LT, "<"),
            Token(TokenType.INT, "10"),
            Token(TokenType.RPAREN, ")"),
            Token(TokenType.LBRACE, "{"),
            Token(TokenType.RETURN, "return"),
            Token(TokenType.TRUE, "true"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.RBRACE, "}"),
            Token(TokenType.ELSE, "else"),
            Token(TokenType.LBRACE, "{"),
            Token(TokenType.RETURN, "return"),
            Token(TokenType.FALSE, "false"),
            Token(TokenType.SEMICOLON, ";"),
            Token(TokenType.RBRACE, "}"),
            Token(TokenType.SEMICOLON, ";"),
        )

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