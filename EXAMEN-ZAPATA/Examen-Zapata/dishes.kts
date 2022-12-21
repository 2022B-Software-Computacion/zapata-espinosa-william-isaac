import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

val pathName  = "resources/txt/test.txt"
val arrayListDishes = Array<dishes>();

fun showMenuRestaurant(){
    println("Módulo PLATILLOS")
    println("Operaciones")
    println("1. Ingresar un nuevo Platillo")
    println("2. Buscar un Platillo")
    println("3. Actualizar un Platillo")
    println("4. Eliminar un Platillo")
}

fun operationMenuDishes(operation: Int){
    try {
        println("Ingrese el número de la Operación a realizar: || Ejemplo: 1")
        val operation = readLine()!!.toInt();
        when (operation) {
            1 ->
        }
    }catch (e:Exception){
        println("Error Platillo! ${e}")
    }
}

fun createDishes(){
    println("Ingrese el nobre del Platillo: ")
    val nameRestaurant = readLine();
    println("El nombre del Platillo: ${nameRestaurant}")

}

fun readDishes(){
    val text = "";
    val file = InputStreamReader(openFileInput(pathName));
    val bf = BufferedReader(file);
    val line = bf.readLine();
    while (line != null) {
        text = text + line;
        line = bf.readLine();
    }

}

fun updateDishes(){

}

fun deleteDishes(){

}