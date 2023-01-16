import java.time.LocalDate

class Dish(
    var nameDish: String,
    var idDish: Int,
    var priceDish: Float,
    var dateCreationDish: LocalDate,
    var availableDish: Boolean
) {
    override fun toString(): String {
        return "$nameDish,$idDish,$priceDish,$dateCreationDish,$availableDish"
    }
}