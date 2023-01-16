import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ListDishes() {

    private val idDishes = RandomUnrepeated(0, 20)

    //Creation Dish
    fun writeDish(pathFile: String, dish: Dish, listDishes: ArrayList<Dish>){
        listDishes.add(dish);
        var file: File? = null
        var fw: FileWriter? = null
        var pw: PrintWriter? = null
        var text = ""
        try {
            file = File(pathFile)
            fw = FileWriter(file, true)//true
            pw = PrintWriter(fw)
            text = text + dish.nameDish + ",";
            text = text + dish.idDish + ",";
            text = text + dish.priceDish + ",";
            text = text + dish.dateCreationDish + ",";
            text  = text + dish.availableDish + "\n";
            fw.write(text);
            println("Platillo registrado con exito!")
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
            reader.close()
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
        }
        return listDishes
    }

    //Update
    fun updateDish(findDish: String, listDishes: ArrayList<Dish>, pathFile: String): ArrayList<Dish>{
        try {
            for (dishFind in listDishes){
                if (dishFind.nameDish == findDish){
                    val indexDish = listDishes.indexOf(dishFind)
                    println("Informacón del Platillo "+"\n")
                    println("1. Nombre del Platillo: "+dishFind.nameDish)
                    println("2. Id: "+dishFind.idDish)
                    println("3. Precio: "+dishFind.priceDish)
                    println("4. Fecha de creación: "+dishFind.dateCreationDish)
                    println("5. Disponible: "+dishFind.availableDish)
                    println("Seleccione la información deseas cambiar: ")
                    when (readLine()!!.toInt()){
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
                            val newId = readLine()!!.toInt()
                            dishFind.idDish = newId
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            val newPrice = readLine()!!.toFloat()
                            dishFind.priceDish = newPrice
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        4 -> {
                            println("Ingrese la nueva información con el formato YYYY-MM-DD:")
                            val newDate = readLine()
                            val newDateCreation = LocalDate.parse(newDate, DateTimeFormatter.ISO_DATE)
                            dishFind.dateCreationDish = newDateCreation
                            listDishes.set(indexDish,dishFind)
                            writeUpdateData(listDishes, pathFile)
                            println("El Platillo se actualizo con exito!")
                            break
                        }
                        5 -> {
                            println("Ingrese la nueva información:")
                            val newAvailable = readLine()!!.toBoolean()
                            dishFind.availableDish = newAvailable
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
            println("Error Update $e")
        }
        return listDishes
    }

    fun writeUpdateData(listDishes: ArrayList<Dish>, pathFile: String){
        try {
            var file: File? = null
            var fw: FileWriter? = null
            var pw: PrintWriter? = null
            var text = ""
            for (dish in listDishes){
                try {
                    file = File(pathFile)
                    fw = FileWriter(file)//true
                    pw = PrintWriter(fw)
                    text = text + dish.nameDish + ",";
                    text = text + dish.idDish + ",";
                    text = text + dish.priceDish + ",";
                    text = text + dish.dateCreationDish + ",";
                    text  = text + dish.availableDish + "\n";
                    fw.write(text);
                }catch (e: Exception){
                    println("Error Write Update Dish $e")
                }finally {
                    try {
                        if(fw !=null){
                            fw.close()
                        }
                    }catch (e: Exception){
                        println("Error Write Update Dish $e")
                    }
                }
            }
        }catch (e: Exception){
            println("Error Update $e")
        }
    }

    fun deletedFile(pathFile: String): Boolean {
        val flag = true
        try{
            val file = File(pathFile)
            val result = file.delete()
            if (result) {
                return flag
            } else {
                return !flag
            }
        }catch (e: Exception){
            println("Error Delete File $e")
        }
        return flag
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
            println("Error Delete Dish $e")
        }
        return listDishes
    }

    //Creation Dish
    fun creationDish():Dish{
        println("Ingresé el nombre del nuevo Platillo:")
        val nameDish = readLine()!!
        val idDish = idDishes.nextInt()
        println("Ingresé el precio del nuevo Platillo:")
        val priceDish = readLine()!!.toFloat()
        println("Ingresé la fecha de creación del Platillo con el formato YYYY-MM-DD:")
        val dateCreationauxDish = readLine()!!
        val dateCreationDish = LocalDate.parse(dateCreationauxDish,
            DateTimeFormatter.ISO_DATE)
        val availableDish = true
        val newDish = Dish(nameDish, idDish, priceDish, dateCreationDish,availableDish)
        return newDish
    }

}