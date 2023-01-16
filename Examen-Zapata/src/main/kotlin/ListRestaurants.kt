import java.io.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ListRestaurants {

    private val idRestaurants = RandomUnrepeated(0, 20)

    //Creation
    fun writeRestaurant(pathFile: String, restaurant: Restaurant, listRestaurants: ArrayList<Restaurant>){
        listRestaurants.add(restaurant)
        var file: File? = null
        var fw: FileWriter? = null
        var pw: PrintWriter? = null
        var text = ""
        try {
            file = File(pathFile)
            fw = FileWriter(file, true)//true
            pw = PrintWriter(fw)
            text = text + restaurant.nameRestaurant + ",";
            text = text + restaurant.idRestaurant + ",";
            text = text + restaurant.ratingRestaurant + ",";
            text = text + restaurant.dateCreationRestaurant + ",";
            text = text + restaurant.availableRestaurant + "\n";
            fw.write(text);
            //fw.write("\n");
            println("Restaurante registrado con exito!")
        }catch (e: Exception){
            println("Error Write Restaurant $e")
        }finally {
            try {
                if(fw !=null){
                    fw.close()
                }
            }catch (e: Exception){
                println("Error Write Restaurant $e")
            }
        }
    }

    //Read
    fun readRestaurants(pathFile: String): ArrayList<Restaurant> {
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
                val newDishFromFile = Restaurant(nameRestaurant, idRestaurant, ratingRestaurant, dateCreationRestauran,
                    availableRestaurant)
                listDishes.add(newDishFromFile)
                result += line
            }
            reader.close()
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
        }
        return listDishes
    }

    //Update
    fun updateRestaurant(findRestaurant: String, listRestaurants: ArrayList<Restaurant>, pathFile: String):
            ArrayList<Restaurant>{
        try {
            for (restaurntFind in listRestaurants){
                if (restaurntFind.nameRestaurant == findRestaurant){
                    val indexDish = listRestaurants.indexOf(restaurntFind)
                    println("Informacón del Restaurante "+"\n")
                    println("1. Nombre del Restaurante: "+restaurntFind.nameRestaurant)
                    println("2. Id: "+restaurntFind.idRestaurant)
                    println("3. Rating: "+restaurntFind.ratingRestaurant)
                    println("4. Fecha de apertura: "+restaurntFind.dateCreationRestaurant)
                    println("5. Disponible: "+restaurntFind.availableRestaurant)
                    println("Seleccione la información deseas cambiar: ")
                    when (readLine()!!.toInt()){
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
                            val newId = readLine()!!.toInt()
                            restaurntFind.idRestaurant = newId
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            val newRatingRestaurant = readLine()!!.toFloat()
                            restaurntFind.ratingRestaurant = newRatingRestaurant
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        4 -> {
                            println("Ingrese la nueva información con el formato YYYY-MM-DD:")
                            val newDate = readLine()
                            val newDateCreation = LocalDate.parse(newDate, DateTimeFormatter.ISO_DATE)
                            restaurntFind.dateCreationRestaurant = newDateCreation
                            listRestaurants.set(indexDish,restaurntFind)
                            writeUpdateData(listRestaurants, pathFile)
                            println("El Restaurante se actualizo con exito!")
                            break
                        }
                        5 -> {
                            println("Ingrese la nueva información:")
                            val newAvailable = readLine()!!.toBoolean()
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
            println("Error Update $e")
        }
        return listRestaurants
    }

    fun writeUpdateData(listRestaurants: ArrayList<Restaurant>, pathFile: String) {
        try {
            var file: File? = null
            var fw: FileWriter? = null
            var pw: PrintWriter? = null
            var text = ""
            for (restaurants in listRestaurants){
                try {
                    file = File(pathFile)
                    fw = FileWriter(file)//true
                    pw = PrintWriter(fw)
                    text = text + restaurants.nameRestaurant + ",";
                    text = text + restaurants.idRestaurant + ",";
                    text = text + restaurants.ratingRestaurant + ",";
                    text = text + restaurants.dateCreationRestaurant + ",";
                    text = text + restaurants.availableRestaurant+"\n";
                    fw.write(text);
                    fw.write("\n");
                }catch (e: Exception){
                    println("Error Write Update Restaurant $e")
                }finally {
                    try {
                        if(fw !=null){
                            fw.close()
                        }
                    }catch (e: Exception){
                        println("Error Write Update Restaurant $e")
                    }
                }
            }
        }catch (e: Exception){
            println("Error Update $e")
        }
    }

    //Delete Dish
    fun deleteRestaurant(findRestaurant: String, listRestaurants: ArrayList<Restaurant>, pathFile: String):
            ArrayList<Restaurant>{
        try {
            for(restaurantFind in listRestaurants){
                if(restaurantFind.nameRestaurant == findRestaurant){
                    listRestaurants.remove(restaurantFind)
                    writeUpdateData(listRestaurants, pathFile)
                    println("Restaurante eliminado con exito!!")
                    break
                }else{
                    println("El Restaurante ingresado no existe, ingrese un Restaurante valido")
                }
            }
        }catch (e: Exception){
            println("Error Delete Dish $e")
        }
        return listRestaurants
    }

    //Creation Restaurant
    fun creationRestaurant(): Restaurant{
        println("Ingresé el nombre del nuevo Restaurante:")
        val name = readLine()!!
        val id = idRestaurants.nextInt()
        println("Ingresé el rating del Restaurante, de 0.0 a 5.0:")
        val rating = readLine()!!.toFloat()
        println("Ingresé la fecha de apertura del Restaurante con el formato YYYY-MM-DD:")
        val dateCreationaux = readLine()
        val dateCreation = LocalDate.parse(dateCreationaux, DateTimeFormatter.ISO_DATE)
        val availableRestaurant = true
        val newRestaurant = Restaurant(name, id, rating, dateCreation, availableRestaurant)
        return newRestaurant
    }
}