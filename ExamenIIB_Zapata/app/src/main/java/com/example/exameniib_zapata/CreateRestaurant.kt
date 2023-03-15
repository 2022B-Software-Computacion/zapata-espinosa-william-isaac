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

class CreateRestaurant : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_restaurant)

        val etNameRestaurant = findViewById<EditText>(R.id.etNameRestaurant)
        val etOpeningDateRestaurant = findViewById<EditText>(R.id.etOpeningDateRestaurant)
        val etRatingRestaurant = findViewById<EditText>(R.id.etRatingRestaurant)
        val etAvailableRestaurant = findViewById<EditText>(R.id.etAvailableRestaurant)

        val btnSaveRestaurant = findViewById<Button>(R.id.btnSaveRestaurant)
        btnSaveRestaurant.setOnClickListener {
            if (etNameRestaurant.text.isNotEmpty()
                && etOpeningDateRestaurant.text.isNotEmpty()
                && etRatingRestaurant.text.isNotEmpty()
                && etAvailableRestaurant.text.isNotEmpty()
            ){
                val transformAvailableRestaurant = etAvailableRestaurant.text.toString()
                val resultAvailableRestaurant = transformAvailableRestaurant.toBoolean()

                val transformRatingRestaurant = etRatingRestaurant.text.toString()
                val resultRatingRestaurant = transformRatingRestaurant.toDouble()

                val data = hashMapOf(
                    "name" to etNameRestaurant.text.toString(),
                    "available" to resultAvailableRestaurant,
                    "openingDate" to etOpeningDateRestaurant.text.toString(),
                    "rating" to resultRatingRestaurant
                )

                db.collection("Restaurant").add(data).
                addOnSuccessListener {
                    Toast.makeText(this, "Successfully created restaurant", Toast.LENGTH_SHORT).show()
                    goActivity(RestaurantMain::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error creating a restaurant", Toast.LENGTH_SHORT).show()
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