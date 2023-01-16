import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val pathRestaurantFile = "Restaurants.txt"
    var pathDishesFile: String
    val menuDish = Menu()
    val menuRestaurant = Menu()
    val listDishesClass = ListDishes()
    val listRestaurantsClass = ListRestaurants()
    var optionMenuRestaurant = true
    var optionMenuDish = true

    println()
    println("Sistema de Gestión de Restaurantes")

    try {
        while (optionMenuRestaurant){
            println()
            menuRestaurant.showMenuRestaurant()
            when(readLine()!!.toInt()){
                1 -> {
                    val newRestaurant = listRestaurantsClass.creationRestaurant()
                    val aux = listRestaurantsClass.readRestaurants(pathRestaurantFile)
                    listRestaurantsClass.writeRestaurant(pathRestaurantFile, newRestaurant, aux)
                    println()
                }
                2 -> {
                    println("Los Restaurantes registrados son: ")
                    val auxReadRestaurant = listRestaurantsClass.readRestaurants(pathRestaurantFile)
                    println(auxReadRestaurant)
                }
                3 -> {
                    println()
                    println("Operaciones: ")
                    println("1. Ir al Módulo de actualización de información del Restaurante: ")
                    println("2. Ir al Módulo de Platillo: ")
                    println("Ingresé el número de la operación a realizar: ")
                    when(readLine()!!.toInt()){
                        1 -> {
                            println()
                            println("Los Restaurantes registrados son: ")
                            val auxReadRestaurant = listRestaurantsClass.readRestaurants(pathRestaurantFile)
                            println(auxReadRestaurant)
                            println()
                            println("Ingresé el nombre del restaurante que desea actualizar:")
                            val restaurantUpdate = readLine()!!
                            println()
                            println("Actualizar información del Restaurante")
                            listRestaurantsClass.updateRestaurant(restaurantUpdate,auxReadRestaurant,
                                pathRestaurantFile)
                        }
                        2 -> {
                            try {
                                println()
                                println("Ingresé el nombre del Restaurante al cual va a pertenecer el platillo:")
                                val restaurantUpdate = readLine()!!
                                pathDishesFile = "$restaurantUpdate.txt"
                                while (optionMenuDish){
                                    println()
                                    menuDish.showMenuDish()
                                    when(readLine()!!.toInt()){
                                        1 -> {
                                            val newDish = listDishesClass.creationDish()
                                            var aux = listDishesClass.readDishes(pathDishesFile)
                                            listDishesClass.writeDish(pathDishesFile, newDish, aux)
                                            println()
                                        }
                                        2 -> {
                                            println("Los Platillos registrados son: ")
                                            val auxReadDishes = listDishesClass.readDishes(pathDishesFile)
                                            println(auxReadDishes)
                                        }
                                        3 -> {
                                            println("Los Platillos registrados son: ")
                                            val auxReadDishes = listDishesClass.readDishes(pathDishesFile)
                                            println(auxReadDishes)
                                            println()
                                            println("Ingresé el nombre del Platillo que desea actualizar: ")
                                            val dishUpdate = readLine()!!
                                            println()
                                            println("Actualizar información del Platillo")
                                            listDishesClass.updateDish(dishUpdate, auxReadDishes, pathDishesFile)
                                        }
                                        4 -> {
                                            println("Los Platillos registrados son: ")
                                            val auxReadDishes = listDishesClass.readDishes(pathDishesFile)
                                            println(auxReadDishes)
                                            println()
                                            println("Ingresé el nombre del Platillo que desea eliminar: ")
                                            val dishDelete = readLine()!!
                                            listDishesClass.deletedDish(dishDelete, auxReadDishes,pathDishesFile)
                                            println()
                                            println("Los Platillos registrados son: ")
                                            val auxReadDishesI = listDishesClass.readDishes(pathDishesFile)
                                            println(auxReadDishesI)
                                        }
                                        5 -> {
                                            println("Atrás... ")
                                            println()
                                            optionMenuDish = false
                                        }
                                        else -> {
                                            println("Error la operación ingresada no existe !!!")
                                        }
                                    }
                                }
                            }catch (e: Exception){
                                println(" Error Módulo Dishes !! $e")
                            }
                        }
                    }
                }
                4 -> {
                    println("Los Restaurantes registrados son: ")
                    val auxReadRestaurant = listRestaurantsClass.readRestaurants(pathRestaurantFile)
                    println(auxReadRestaurant)
                    println()
                    println("Ingresé el nombre del Restaurante que desea eliminar: ")
                    val restaurantDelete = readLine()!!
                    val auxI = listRestaurantsClass.deleteRestaurant(restaurantDelete,auxReadRestaurant,pathRestaurantFile)
                    println("Los Restaurantes registrados son: ")
                    println(auxI)
                }
                5 -> {
                    println("Adiós")
                    optionMenuRestaurant = false
                }
            }
        }
    }catch (e: Exception){
        println("Error Menu $e")
    }
}


