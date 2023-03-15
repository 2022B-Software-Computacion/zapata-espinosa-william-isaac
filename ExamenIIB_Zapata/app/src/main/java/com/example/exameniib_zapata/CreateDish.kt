package com.example.exameniib_zapata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateDish : AppCompatActivity() {
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dish)


        val etNameDish = findViewById<EditText>(R.id.etNameDish)
        val etCreationDateDish  = findViewById<EditText>(R.id.etCreationDateDish)
        val etPriceDish  = findViewById<EditText>(R.id.etPriceDish)
        val etAvailableDish  = findViewById<EditText>(R.id.etAvailableDish)

        val btnSaveDish = findViewById<Button>(R.id.btnSaveDish)
        btnSaveDish.setOnClickListener {
            if (etNameDish.text.isNotEmpty()
                && etCreationDateDish.text.isNotEmpty()
                && etPriceDish.text.isNotEmpty()
                && etAvailableDish.text.isNotEmpty()
            ){
                val transformAvailableDish = etAvailableDish.text.toString()
                val resultAvailableDish = transformAvailableDish.toBoolean()

                val transformPriceDish = etPriceDish.text.toString()
                val resultPriceDish = transformPriceDish.toDouble()

                val data = hashMapOf(
                    "name" to etNameDish.text.toString(),
                    "available" to resultAvailableDish,
                    "creationDate" to etCreationDateDish.text.toString(),
                    "price" to resultPriceDish
                )

                db.collection("Dish").add(data).
                addOnSuccessListener {
                    Toast.makeText(this, "Successfully created dish", Toast.LENGTH_SHORT).show()
                    goActivity(RestaurantMain::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error creating a dish", Toast.LENGTH_SHORT).show()
                    goActivity(RestaurantMain::class.java)
                }
            }
        }
    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}