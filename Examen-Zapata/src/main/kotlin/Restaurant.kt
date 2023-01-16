import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Restaurant(
    var nameRestaurant: String,
    var idRestaurant: Int,
    var ratingRestaurant: Float,
    var dateCreationRestaurant: LocalDate,
    var availableRestaurant: Boolean
) {

    override fun toString(): String {
        return "$nameRestaurant,$idRestaurant,$ratingRestaurant,$dateCreationRestaurant,$availableRestaurant"
    }
}