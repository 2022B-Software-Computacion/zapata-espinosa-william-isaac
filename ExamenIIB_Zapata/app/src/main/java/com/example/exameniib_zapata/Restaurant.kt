package com.example.exameniib_zapata

data class Restaurant(
    var id: String = "",
    var name: String = "",
    var rating: Double = 0.0,
    var openingDate: String = "",//LocalDate
    var available: Boolean? = null
)