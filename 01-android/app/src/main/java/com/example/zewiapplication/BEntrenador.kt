package com.example.zewiapplication

class BEntrenador(
    val nombre: String?,
    val description: String?
) {

    override fun toString(): String {
        return "${nombre} - ${description}"
    }
}