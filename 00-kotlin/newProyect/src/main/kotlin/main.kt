import java.util.*

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
    ejemploVarible.trip()


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
    val estadoCivilWhen = 'S'
    when(estadoCivilWhen) {
        ("S") -> {
            print("Esta soltero")

        }
        "C" -> {
            print("Alejarse")

        }
        else -> print("No reconcidos")

    }



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

}

