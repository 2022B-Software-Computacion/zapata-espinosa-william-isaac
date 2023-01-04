import java.io.BufferedReader
import java.io.File
import java.time.LocalDate

class Dish(
    var nameDish: String,
    var idDishe: Int,
    var priceDishe: Float,
    var dateCreationDishe: LocalDate,
    var availableDishe: Boolean
) {

    override fun toString(): String {
        return "$nameDish,$idDishe,$priceDishe,$dateCreationDishe,$availableDishe"
    }

}