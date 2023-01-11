package com.example.zewiapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",// nombre de la BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCreateTableEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCreateTableEntrenador)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        fun crearEntrenador(
            nombre: String,
            descripcion: String
        ): Boolean{
            val basedatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombre", nombre)
            valoresAGuardar.put("descripcion", descripcion)
            val resultadoGuardar = basedatosEscritura
                .insert(
                    "ENTRENADOR",//Nombre tabla
                    null,
                    valoresAGuardar // valores
                )
            basedatosEscritura.close()
            return if(resultadoGuardar.toInt() == -1) false else true
        }
    }

}