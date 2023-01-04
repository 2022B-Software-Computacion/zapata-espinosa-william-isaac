package com.example.zewiapplication

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador("William", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Isaac", "b@b.com")
                )
        }
    }
}