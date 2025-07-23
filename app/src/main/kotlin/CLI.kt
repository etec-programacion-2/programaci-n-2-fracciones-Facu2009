package org.example

import java.util.InputMismatchException
import java.util.Scanner

fun mostrarMenu() {
    println("=== CALCULADORA DE FRACCIONES ===")
    println("1. Sumar fracciones")
    println("2. Restar fracciones")
    println("3. Multiplicar fracciones")
    println("4. Dividir fracciones")
    println("5. Comparar fracciones")
    println("6. Convertir fracción a decimal")
    println("7. Crear fracción desde decimal")
    println("8. Ejemplos predefinidos")
    println("0. Salir")
    print("Ingrese su opción: ")
}

fun leerFraccion(scanner: Scanner, mensaje: String): Fraccion {
    while (true) {
        try {
            println(mensaje)
            print("Numerador: ")
            val numerador = scanner.nextInt()
            print("Denominador: ")
            val denominador = scanner.nextInt()
            if (denominador == 0) {
                println("Denominador no puede ser cero. Intente nuevamente.")
                scanner.nextLine() // limpiar buffer
                continue
            }
            return Fraccion(numerador, denominador).simplificada()
        } catch (e: InputMismatchException) {
            println("Entrada inválida. Por favor, ingrese números enteros.")
            scanner.nextLine() // limpiar buffer
        }
    }
}

fun realizarSuma(scanner: Scanner) {
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción para sumar:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción para sumar:")
    val resultado = f1 + f2
    println("Resultado: $f1 + $f2 = $resultado")
}

fun realizarResta(scanner: Scanner) {
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción para restar:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción para restar:")
    val resultado = f1 - f2
    println("Resultado: $f1 - $f2 = $resultado")
}

fun realizarMultiplicacion(scanner: Scanner) {
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción para multiplicar:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción para multiplicar:")
    val resultado = f1 * f2
    println("Resultado: $f1 * $f2 = $resultado")
}

fun realizarDivision(scanner: Scanner) {
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción para dividir:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción para dividir:")
    try {
        val resultado = f1 / f2
        println("Resultado: $f1 / $f2 = $resultado")
    } catch (e: ArithmeticException) {
        println("Error: ${e.message}")
    }
}

fun realizarComparacion(scanner: Scanner) {
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción para comparar:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción para comparar:")
    when {
        f1 == f2 -> println("Las fracciones $f1 y $f2 son iguales.")
        f1 > f2 -> println("La fracción $f1 es mayor que $f2.")
        else -> println("La fracción $f1 es menor que $f2.")
    }
}

fun convertirADecimal(scanner: Scanner) {
    val f = leerFraccion(scanner, "Ingrese la fracción para convertir a decimal:")
    println("La fracción $f en decimal es ${f.aDecimal()}")
}

fun crearDesdeDecimal(scanner: Scanner) {
    while (true) {
        try {
            print("Ingrese un número decimal: ")
            val decimal = scanner.nextDouble()
            val f = Fraccion.desdeDecimal(decimal).simplificada()
            println("La fracción equivalente a $decimal es $f")
            break
        } catch (e: InputMismatchException) {
            println("Entrada inválida. Intente nuevamente.")
            scanner.nextLine() // limpiar buffer
        }
    }
}

fun mostrarEjemplos() {
    println("\n=== EJEMPLOS PREDEFINIDOS ===")

    val f1 = Fraccion(1, 2)  // 1/2
    val f2 = Fraccion(1, 3)  // 1/3

    println("Fracción 1: $f1")
    println("Fracción 2: $f2")
    println("Suma: $f1 + $f2 = ${f1 + f2}")
    println("Resta: $f1 - $f2 = ${f1 - f2}")
    println("Multiplicación: $f1 * $f2 = ${f1 * f2}")
    println("División: $f1 / $f2 = ${f1 / f2}")
    println("¿$f1 > $f2? ${f1 > f2}")
    println("$f1 en decimal: ${f1.aDecimal()}")
}

fun main() {
    val scanner = Scanner(System.`in`)
    var opcion: Int

    do {
        mostrarMenu()
        try {
            opcion = scanner.nextInt()
        } catch (e: InputMismatchException) {
            println("Entrada inválida. Por favor ingrese un número.")
            scanner.nextLine() // limpiar buffer
            opcion = -1
            continue
        }

        when (opcion) {
            1 -> realizarSuma(scanner)
            2 -> realizarResta(scanner)
            3 -> realizarMultiplicacion(scanner)
            4 -> realizarDivision(scanner)
            5 -> realizarComparacion(scanner)
            6 -> convertirADecimal(scanner)
            7 -> crearDesdeDecimal(scanner)
            8 -> mostrarEjemplos()
            0 -> println("¡Hasta luego!")
            else -> println("Opción inválida. Intente de nuevo.")
        }

        if (opcion != 0) {
            println("\nPresione Enter para continuar...")
            scanner.nextLine() // limpiar buffer pendiente
            scanner.nextLine() // esperar Enter
        }

    } while (opcion != 0)

    scanner.close()
}
