package com.example.zewiapplication

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "William", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Isaac", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Juan", "c@b.com")
                )
        }
    }
}