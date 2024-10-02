package parser

import org.example.ast.LetStatement
import org.example.lexer.Lexer
import org.example.parser.Parser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
}