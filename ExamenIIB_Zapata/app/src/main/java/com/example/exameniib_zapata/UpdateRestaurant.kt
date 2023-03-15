package com.example.exameniib_zapata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Objects

class UpdateRestaurant : AppCompatActivity() {
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_restaurant)

        val bundle = intent.extras
        val idRestaurant = bundle?.getString("id")
        //Toast.makeText(this, "Id:   $idRestaurant", Toast.LENGTH_SHORT).show()
        var docRef = idRestaurant?.let { db.collection("Restaurant").document(it) }

        val etNameRestaurantUpdate = findViewById<EditText>(R.id.etNameRestaurantUpdate)
        val etOpeningDateRestaurantUpdate  = findViewById<EditText>(R.id.etOpeningDateRestaurantUpdate )
        val etRatingRestaurantUpdate  = findViewById<EditText>(R.id.etRatingRestaurantUpdate )
        val etAvailableRestaurantUpdate  = findViewById<EditText>(R.id.etAvailableRestaurantUpdate)
        val btnUpdateRestaurant = findViewById<Button>(R.id.btnRestaurantUpdate)

        if (docRef != null) {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Access the DocumentSnapshot data here.
                    val name = documentSnapshot.getString("name")
                    val availableAux = documentSnapshot.getBoolean("available")
                    val available = availableAux.toString()
                    val openingDate = documentSnapshot.getString("openingDate")
                    val ratingAux = documentSnapshot.getDouble("rating")
                    val rating = ratingAux.toString()


                    etNameRestaurantUpdate.setText(name)
                    etOpeningDateRestaurantUpdate.setText(openingDate)
                    etRatingRestaurantUpdate.setText(rating)
                    etAvailableRestaurantUpdate.setText(available)
                }
            }
        }

        btnUpdateRestaurant.setOnClickListener {
            if (etNameRestaurantUpdate.text.isNotEmpty()
                && etOpeningDateRestaurantUpdate.text.isNotEmpty()
                && etRatingRestaurantUpdate.text.isNotEmpty()
                && etAvailableRestaurantUpdate.text.isNotEmpty()){

                val transformAvailableRestaurantUpdate = etAvailableRestaurantUpdate.text.toString()
                val resultAvailableRestaurantUpdate = transformAvailableRestaurantUpdate.toBoolean()

                val transformRatingRestaurantUpdate = etRatingRestaurantUpdate.text.toString()
                val resultRatingRestaurantUpdate = transformRatingRestaurantUpdate.toDouble()

                val data = hashMapOf(
                    "name" to etNameRestaurantUpdate.text.toString(),
                    "available" to resultAvailableRestaurantUpdate,
                    "openingDate" to etOpeningDateRestaurantUpdate.text.toString(),
                    "rating" to resultRatingRestaurantUpdate
                )

                if (idRestaurant != null) {
                    db.collection("Restaurant").document(idRestaurant).update(data as Map<String, Any>).
                    addOnSuccessListener {
                        Toast.makeText(this, "Successfully updated restaurant", Toast.LENGTH_SHORT).show()
                        goActivity(RestaurantMain::class.java)
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error updating a restaurant", Toast.LENGTH_SHORT).show()
                        goActivity(RestaurantMain::class.java)
                    }
                }
            }
        }
    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}