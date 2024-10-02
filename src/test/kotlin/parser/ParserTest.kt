package parser

import org.example.ast.LetStatement
import org.example.lexer.Lexer
import org.example.parser.Parser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ParserTest {
    @Test
    fun testLetStatements() {
        val input = """
        let x = 5;
        let y = 10;
        let foobar = 838383;
        """
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        val program = parser.parseProgram()
        assertEquals(3, program.statements.size)
        val tests = listOf("x", "y", "foobar")
        program.statements.forEachIndexed { i, stmt ->
            val letStmt = stmt as LetStatement
            assertEquals("let", letStmt.tokenLiteral())
            assertEquals(tests[i], letStmt.name?.value)
            assertEquals(tests[i], letStmt.name?.tokenLiteral())
        }
    }

    @Test
    fun testLetStatements2() {
        val input = """
        let x 5;
        let = 10;
        let 838383;
        """
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        val errors = parser.errors()
        checkParserErrors(parser)
        val expectedErrors = listOf(
            "expected next token to be =, got 5 instead",
            "expected next token to be IDENT, got = instead",
            "expected next token to be IDENT, got 838383 instead"
        )
        errors.forEachIndexed { i, error ->
            assertEquals(expectedErrors[i], error)
        }
    }

    private fun checkParserErrors(parser: Parser) {
        val errors = parser.errors()
        if (errors.isEmpty()) {
            return
        }

        println("parser has ${errors.size} errors")
        for (msg in errors) {
            println("parser error: \"$msg\"")
        }
        fail("Parser errors found")
    }
}