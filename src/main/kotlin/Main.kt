package org.example

import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val user = System.getProperty("user.name")
    println("Hello $user! This is the Monkey programming language!")
    println("Feel free to type in commands")
    repl.start(InputStreamReader(System.`in`), OutputStreamWriter(System.out))
}