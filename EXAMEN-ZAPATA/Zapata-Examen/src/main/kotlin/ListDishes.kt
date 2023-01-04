import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ListDishes() {

    //Creation Dishes
    fun WriteDishes(pathFile: String, dish: Dish, listDishes: ArrayList<Dish>){
        listDishes.add(dish);
        try {
            File(pathFile).delete()
            for (dishes in listDishes){
                File(pathFile).appendText(dishes.toString()+"\n");
            }
        }catch (e:Exception){
            println("Error File Write ${e}")
        }
    }

    //Read Dishes
    fun readDishes(pathFile: String): ArrayList<Dish> {
        val listDishes = ArrayList<Dish>()
        try {
            var result = ""
            var line = ""
            val reader = File(pathFile).bufferedReader()
            while (reader.readLine().also { line = it } != null) {
                val tokens = StringTokenizer(line, ",")
                var data = tokens.nextToken()
                val nameDish = data;
                data = tokens.nextToken()
                val idDishe = data.toInt();
                data = tokens.nextToken()
                val priceDishe = data.toFloat();
                data = tokens.nextToken()
                val dateCreationDisheS = data
                data = tokens.nextToken()
                val availableDishe = data.toBoolean()

                val dateCreationDishe = LocalDate.parse(dateCreationDisheS, DateTimeFormatter.ISO_DATE)
                val newDishFromFile = Dish(nameDish, idDishe, priceDishe, dateCreationDishe, availableDishe)
                listDishes.add(newDishFromFile)
                result += line
            }
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
        }
        return listDishes
    }

    //Update
    fun updateDishe(findDish: String, listDishes: ArrayList<Dish>, pathFile: String): ArrayList<Dish>{
        try {
            for (dishFind in listDishes){
                if (dishFind.nameDish == findDish){
                    val indexDish = listDishes.indexOf(dishFind)
                    println("Informacón del Platillo "+"\n")
                    println("1. Nombre del Platillo: "+dishFind.nameDish)
                    println("2. Id: "+dishFind.idDishe)
                    println("3. Precio: "+dishFind.priceDishe)
                    println("4. Fecha de creación: "+dishFind.dateCreationDishe)
                    println("5. Disponible: "+dishFind.availableDishe)
                    println("Seleccione la información deseas cambiar: ")
                    val option = readLine()!!.toInt()
                    when (option){
                        1 -> {
                            println("Ingrese la nueva información:")
                            val newName = readLine()
                            dishFind.nameDish = newName.toString()
                            listDishes[indexDish] = dishFind
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        2 -> {
                            println("Ingrese la nueva información:")
                            var newId = readLine()!!.toInt()
                            dishFind.idDishe = newId
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            var newPrice = readLine()!!.toFloat()
                            dishFind.priceDishe = newPrice
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        4 -> {
                            println("Ingrese la nueva información con el formato YYYY-MM-DD:")
                            var newDate = readLine()
                            val newDateCreation = LocalDate.parse(newDate, DateTimeFormatter.ISO_DATE)
                            dishFind.dateCreationDishe = newDateCreation
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        5 -> {
                            println("Ingrese la nueva información:")
                            var newAvailable = readLine()!!.toBoolean()
                            dishFind.availableDishe = newAvailable
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        else -> {
                            println("Error la operación ingresada no existe !!!")
                            break
                        }
                    }
                }else{
                    println("El Platillo ingresado no existe, ingrese un Platillo valido")
                }
            }
        }catch (e:Exception){
            println("Error Update ${e}")
        }
        return listDishes
    }

    fun writeUpdateData(listDishes: ArrayList<Dish>, pathFile: String) {

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


    //Delete Dish
    fun deletedDish(findDish: String, listDishes: ArrayList<Dish>, pathFile: String): ArrayList<Dish>{
        try {
            for(dishFind in listDishes){
                if(dishFind.nameDish == findDish){
                    listDishes.remove(dishFind)
                    writeUpdateData(listDishes, pathFile)
                    println("Platillo eliminado con exito!!")
                    break;
                }else{
                    println("El Platillo ingresado no existe, ingrese un Platillo valido")
                }
            }
        }catch (e: Exception){
            println("Error Delete Dish ${e}")
        }
        return listDishes
    }






}