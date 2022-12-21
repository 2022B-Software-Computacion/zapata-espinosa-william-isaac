import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

val pathName  = "resources/txt/test.txt"


fun showMenuRestaurant(){
    println("Módulo RESTAURANTES")
    println("Operaciones")
    println("1. Ingresar un nuevo Restaurante")
    println("2. Buscar un Restaurante")
    println("3. Actualizar un Restaurante")
    println("4. Eliminar un Restaurante")
}

fun operationMenuRestaurant(operation: Int){
    try {
        println("Ingrese el número de la Operación a realizar: || Ejemplo: 1")
        val operation = readLine()!!.toInt();
        when (operation) {
            1 ->
        }
    }catch (e:Exception){
        println("Error Restaurante! ${e}")
    }
}

fun createRestaurant(){
    println("Ingrese el nobre del restaurante: ")
    val nameRestaurant = readLine();
    println("El nombre del restaurante: ${nameRestaurant}")
}

fun readRestaurant(){
    val text = "";
    val file = InputStreamReader1(openFileInput(pathName));
    val bf = BufferedReader(file);
    val line = bf.readLine();
    while (line != null) {
        text = text + line;
        line = bf.readLine();
    }

}

fun updateRestaurant(){

}

fun deleteRestaurant(){

}