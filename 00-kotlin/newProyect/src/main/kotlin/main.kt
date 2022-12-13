import java.util.*
import kotlin.collections.ArrayList

// Main.kt
fun manin(){
    print("Hello world")


    // Tipos de variables

    //INMUTABLES: No se puede re asignar
    //Se recomienda utilizar siempre esta
    val inmutable: String = "William";
    //Esto no se puede hacer
    // unmutable = "Isaac"


    //MUTABLE
    var mutable: String = "Zapata";
    mutable = "Espinosa"


    //Sintaxis Duck typing
    val ejemploVariable = "Ejemplo"
    val edadEjemplo: Int = 12
    //ejemploVarible.trip()


    // VARIABLE PRIMITIVAS
    val nambreProfessor = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'

    val esMayorEdad: Boolean = true


    //Clases JAVA
    val fechaNacimiento: Date = Date()


    //if
    if(true){

    }else{

    }


    //SWITCH no existe
/*    val estadoCivilWhen = 'S'
    when(estadoCivilWhen) {
        ("S") -> {
            print("Esta soltero")

        }
        "C" -> {
            print("Alejarse")

        }
        else -> print("No reconcidos")

    }*/

    val sumaUno = Suma(1,2)
    sumaUno.sumar()
    Suma.pi
    Suma.elevarAlCuadrado(2)
    Suma.historialSumas

    //void imprimirNombre(String nombre){}
    // Unit == void (java)


    fun imprintName(name: String){
        println("Name: ${name}")
    }

    fun calculateSueldo(
        sueldo: Double,
        tasaEspecial: Double,
        bonoEspecial: Double? = null // Opcional (Null) = nullable
    ): Double {
        if(bonoEspecial == null){
            return sueldo * (100 / tasaEspecial)
        }else{
            return sueldo * (100 / tasaEspecial)
        }
    }


    //ARREGLOS

    //Tipos de arreglos

    ///Arregllo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    println(arregloDinamico)

    //OPERADORES -> Sirven para los arreglos estáticos y dinamicos


    //FOR EACH -> Unit
    //Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach {
            valorActual:Int -> println("Valor actual: ${valorActual}")
        }

    //Iterar un arrglo
    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP Muta ek arreglo ( cambia el arreglo)
    //1. Enviamos el nuevo  valor de la iteracion
    //2. Nos devuelve es un nuevo arreglo con los valores modificados


    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)


    val respuestaMapDos = arregloDinamico.map {it + 15}
    // .map { valorActual: Int ->
        // return@map valorActual + 15
    //}

    println(respuestaMapDos)

    // OR AND
    // OR -> ANY (ALGUNO CUMPLE?)
    // AND -> ALL (TODOS CUMPLE)

    val respuestaAny: Boolean = arregloDinamico
        .any{
            valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny)//true

    val respuestaAll: Boolean = arregloDinamico
        .all{
            valorACtual: Int ->
            return@all (valorACtual > 5)
        }
    println(respuestaAll) //false

    //REDUCE -> VALOR ACUMULADO
    // VALOR ACUMULADO = 0 (SIEMPRE 0 EN EL LENGUAJE KOTLIN
    //[1,2,3,4,5] -> SUME TODOS LOS VALORES DEL ARRAY
    //valorIteracion1 = valorEmpieza + 1 = 0+1 = 1 ->Iteracion 1
    //valorIteracion2 = valorIteracion1 +2 = 1 + 2 = 3 -> Iteracion 2
    //valorIteracion3 = valorIteracion2 +3 = 3+3 = 6 -> Iteracion3
    //valorIteracion4 = valorIteracion3 + 4 = 6 +4 = 10 -> Iteracion4
    //valorIteracion5 = valorIteracion4 +5 = 10 + 5 = 15 -> Iteracion5

    val respuestaReduce : Int = arregloDinamico
        .reduce {// acumulado = 0 -> SIEMPRE EMPIEZA en O
            acumulado: Int, valorActual: Int ->
            return@reducen (acumulado + valorActual)
        }
    print(respuestaReduce)

}

abstract class NumerosJava{
    protected val numeroUno: Int
    protected val numeroDos: Int


    constructor(
        uno: Int,
        dos: Int
    ){//Bloque cododigo constructor
        this.numeroUno = uno
        this.numeroDos = dos
        print("Inicializado")
    }
}


abstract class Numeros(// CONSTRUCTOR PRIMARIO
    //uno: Int, // Parametro
    //Public es obsional, por default es public
    //var uno:Int
   // public var uno: Int, // Propiedad de la clase publica

    protected val numeroUno: Int,
    protected val numeroDos: Int

){
    init{// BLoque de codigo constructor PRIMARIO
        //this.numeroUno = uno ya no se hace
        this.numeroUno
        numeroDos// No es necesario utilizar this
        print("Inicializado")
    }

}

class Suma(// Constructor Primario
    uno: Int,
    dos: Int
): Numeros(uno, dos){
    init {// Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor(//Segundo contructor
        uno: Int?,
        dos: Int
    ):this(//llamada constructor primario
        if(uno == null) 0 else uno,
        dos
    )

    constructor(//Segundo contructor
        uno: Int,
        dos: Int?
    ):this(//llamada constructor primario
        if(dos == null) 0 else dos,
        uno
    )

    constructor(//Segundo contructor
        uno: Int?,
        dos: Int?
    ):this(//llamada constructor primario
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos
    )

    public fun sumar(): Int{
        return numeroUno + numeroDos
    }

    companion object{//Atributos y metodos "Compartidos" entre las instancias
        val pi = 3.14
        fun  elevarAlCuadrado(num: Int): Int {
            return  num*num
        }

        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevoSum: Int){
            historialSumas.add(valorNuevoSum)
        }
    }




}

