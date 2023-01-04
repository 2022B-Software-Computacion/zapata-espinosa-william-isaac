import java.time.LocalDate

class Restaurant(
    var nameRestaurant: String,
    var idRestaurant: Int,
    var ratingRestaurant: Float,
    var dateCreationRestaurant: LocalDate,
    var availableRestaurant: Boolean
) {
    val listDishes = ListDishes()
    val listRestaurants = ListRestaurants()

    override fun toString(): String {
        return "$nameRestaurant,$idRestaurant,$ratingRestaurant,$dateCreationRestaurant,$availableRestaurant"
    }



}