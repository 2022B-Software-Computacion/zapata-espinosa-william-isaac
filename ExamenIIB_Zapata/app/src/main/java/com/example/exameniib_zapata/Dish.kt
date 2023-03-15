package com.example.exameniib_zapata

data class Dish(
    var id: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var creationDate: String = "",//LocalDate
    var available: Boolean? = null
)