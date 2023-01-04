import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ListRestaurants {

    //Creation
    fun WriteDishestest(pathFile: String, restaurant: Restaurant, listRestaurants: ArrayList<Restaurant>){
        listRestaurants.add(restaurant);
        try {
            File(pathFile).delete()
            for (dishes in listRestaurants){
                File(pathFile).appendText(restaurant.toString()+"\n");
            }
        }catch (e:Exception){
            println("Error File Write ${e}")
        }
    }

    //Read
    fun verT(pathFile: String): ArrayList<Restaurant> {
        val listDishes = ArrayList<Restaurant>()
        try {
            var result = ""
            var line = ""
            val reader = File(pathFile).bufferedReader()
            while (reader.readLine().also { line = it } != null) {
                val tokens = StringTokenizer(line, ",")
                var data = tokens.nextToken()
                val nameRestaurant = data;
                data = tokens.nextToken()
                val idRestaurant = data.toInt();
                data = tokens.nextToken()
                val ratingRestaurant = data.toFloat();
                data = tokens.nextToken()
                val dateCreationRestaurant = data
                data = tokens.nextToken()
                val availableRestaurant = data.toBoolean()

                val dateCreationRestauran = LocalDate.parse(dateCreationRestaurant, DateTimeFormatter.ISO_DATE)
                val newDishFromFile = Restaurant(nameRestaurant, idRestaurant, ratingRestaurant, dateCreationRestauran, availableRestaurant)
                listDishes.add(newDishFromFile)
                result += line

            }
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
        }
        return listDishes
    }

    //Update
    fun updateRestaurant(findRestaurant: String, listRestaurants: ArrayList<Restaurant>, pathFile: String): ArrayList<Restaurant>{
        try {
            for (restaurntFind in listRestaurants){
                if (restaurntFind.nameRestaurant == findRestaurant){
                    val indexDish = listRestaurants.indexOf(restaurntFind)
                    println("Informacón del Restaurante "+"\n")
                    println("1. Nombre del Restaurante: "+restaurntFind.nameRestaurant)
                    println("2. Id: "+restaurntFind.idRestaurant)
                    println("3. Precio: "+restaurntFind.ratingRestaurant)
                    println("4. Fecha de creación: "+restaurntFind.dateCreationRestaurant)
                    println("5. Disponible: "+restaurntFind.availableRestaurant)
                    println("Seleccione la información deseas cambiar: ")
                    val option = readLine()!!.toInt()
                    when (option){
                        1 -> {
                            println("Ingrese la nueva información:")
                            val newName = readLine()
                            restaurntFind.nameRestaurant = newName.toString()
                            listRestaurants[indexDish] = restaurntFind
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        2 -> {
                            println("Ingrese la nueva información:")
                            var newId = readLine()!!.toInt()
                            restaurntFind.idRestaurant = newId
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            var newRatingRestaurant = readLine()!!.toFloat()
                            restaurntFind.ratingRestaurant = newRatingRestaurant
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        4 -> {
                            println("Ingrese la nueva información con el formato YYYY-MM-DD:")
                            var newDate = readLine()
                            val newDateCreation = LocalDate.parse(newDate, DateTimeFormatter.ISO_DATE)
                            restaurntFind.dateCreationRestaurant = newDateCreation
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        5 -> {
                            println("Ingrese la nueva información:")
                            var newAvailable = readLine()!!.toBoolean()
                            restaurntFind.availableRestaurant = newAvailable
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        else -> {
                            println("Error la operación ingresada no existe !!!")
                            break
                        }
                    }
                }else{
                    println("El Restaurante ingresado no existe, ingrese un Restaurante valido")
                }
            }
        }catch (e:Exception){
            println("Error Update ${e}")
        }
        return listRestaurants
    }

    fun writeUpdateData(listDishes: ArrayList<Restaurant>, pathFile: String) {

        try {
            deletedFile(pathFile)
            for (dishes in listDishes) {
                File(pathFile).appendText(dishes.toString() + "\n");
            }
        } catch (e: Exception) {
            println("Error File Update ${e}")
        }
    }

    fun deletedFile(pathFile: String) {
        try {
            val file = File(pathFile)
            val result = file.delete()
            if (result) {
                // println("Deletion succeeded.")
            } else {
                println("Deletion failed.")
            }
        }catch (e: Exception){
            println("Error Delete File ${e}")
        }
    }

}