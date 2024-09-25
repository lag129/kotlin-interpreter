package repl

import org.example.lexer.Lexer
import org.example.token.Token
import org.example.token.TokenType
import java.io.BufferedReader
import java.io.Reader
import java.io.Writer

const val PROMPT = ">> "

fun start(input: Reader, output: Writer) {
    val reader = BufferedReader(input)

    while (true) {
        output.write(PROMPT)
        output.flush()
        val line = reader.readLine() ?: return

        val lexer = Lexer(line)

        var token: Token
        do {
            token = lexer.nextToken()
            output.write("$token\n")
            output.flush()
        } while (token.type != TokenType.EOF)
    }
}