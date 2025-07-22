package org.example


class Fraccion(
   private var _numerador: Int,
   private var _denominador: Int
) {


   var numerador: Int
       get() = _numerador
       set(value) {
           _numerador = value
       }


   var denominador: Int
       get() = _denominador
       set(value) {
           _denominador = value
       }


   operator fun plus(otra: Fraccion): Fraccion {
       var nuevo_numerador = this._numerador * otra.
       _denominador + this._denominador * otra._numerador
       var nuevo_denominador = this._denominador * otra._denominador
      
       return Fraccion(nuevo_numerador, nuevo_denominador).simplificada()
   }   


   operator fun minus(otra: Fraccion): Fraccion {
       val nuevo_numerador = this._numerador * otra._denominador - this._denominador * otra._numerador
       val nuevo_denominador = this._denominador * otra._denominador
       return Fraccion(nuevo_numerador, nuevo_denominador).simplificada()
   }


   operator fun times(otra: Fraccion): Fraccion {
       val nuevo_numerador = this._numerador * otra._numerador
       val nuevo_denominador = this._denominador * otra._denominador
       return Fraccion(nuevo_numerador, nuevo_denominador).simplificada()
   }


   operator fun div(otra: Fraccion): Fraccion {
       if (otra._numerador == 0) {
           throw ArithmeticException("División por cero no permitida")
       }
       val nuevo_numerador = this._numerador * otra._denominador
       val nuevo_denominador = this._denominador * otra._numerador
       return Fraccion(nuevo_numerador, nuevo_denominador).simplificada()
   }
   override fun equals(other: Any?): Boolean {
       if (this === other) return true
       if (other !is Fraccion) return false
       return this.numerador == other.numerador && this.denominador == other.denominador
   }    override fun compareTo(otra: Fraccion): Int {
       val diferencia = this.numerador * otra.denominador - otra.numerador * this.denominador
       return diferencia.compareTo(0)
   }    fun esMayor(otra: Fraccion): Boolean = this > otra    fun esMenor(otra: Fraccion): Boolean = this < otra    fun aDecimal(): Double = numerador.toDouble() / denominador    companion object {
       fun desdeDecimal(decimal: Double): Fraccion {
           val precision = 1_000_000
           val numerador = (decimal * precision).toInt()
           val denominador = precision
           return Fraccion(numerador, denominador)
       }
   }
   fun obtenesValor(): String {
       return toString()
   }


   override fun toString(): String {
       return "$numerador / $denominador"
   }


   private fun simplificar(): Fraccion {
       val mcd = mcd(numerador, denominador)
       return Fraccion(numerador / mcd, denominador / mcd)
   }
   private fun simplificada(): Fraccion {
       return this.simplificar()
   }
   private fun mcd(a: Int, b: Int): Int {
       return if (b == 0) a else mcd(b, a % b)
    }
}