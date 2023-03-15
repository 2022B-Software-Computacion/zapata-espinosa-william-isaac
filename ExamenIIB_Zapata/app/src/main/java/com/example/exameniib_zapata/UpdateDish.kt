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

class UpdateDish : AppCompatActivity() {
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_dish)

        val bundle = intent.extras
        val idDish = bundle?.getString("id")

        var docRef = idDish?.let { db.collection("Dish").document(it) }

        val etNameDishUpdate = findViewById<EditText>(R.id.etNameDishUpdate)
        val etCreationDateDishUpdate  = findViewById<EditText>(R.id.etCreationDateDishUpdate )
        val etPriceDishUpdate  = findViewById<EditText>(R.id.etPriceDishUpdate )
        val etAvailableDishUpdate  = findViewById<EditText>(R.id.etAvailableDishUpdate)
        val btnDishUpdate = findViewById<Button>(R.id.btnDishUpdate)

        if (docRef != null) {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Access the DocumentSnapshot data here.
                    val name = documentSnapshot.getString("name")
                    val availableAux = documentSnapshot.getBoolean("available")
                    val available = availableAux.toString()
                    val creationDate = documentSnapshot.getString("creationDate")
                    val priceAux = documentSnapshot.getDouble("price")
                    val price = priceAux.toString()


                    etNameDishUpdate.setText(name)
                    etCreationDateDishUpdate.setText(creationDate)
                    etPriceDishUpdate.setText(price)
                    etAvailableDishUpdate.setText(available)
                }
            }
        }

        btnDishUpdate.setOnClickListener {
            if (etNameDishUpdate.text.isNotEmpty()
                && etCreationDateDishUpdate.text.isNotEmpty()
                && etPriceDishUpdate.text.isNotEmpty()
                && etAvailableDishUpdate.text.isNotEmpty()){

                val transformAvailableDishUpdate = etAvailableDishUpdate.text.toString()
                val resultAvailableDishUpdate = transformAvailableDishUpdate.toBoolean()

                val transformPriceDishUpdate = etPriceDishUpdate.text.toString()
                val resultPriceDishUpdate = transformPriceDishUpdate.toDouble()

                val data = hashMapOf(
                    "name" to etNameDishUpdate.text.toString(),
                    "available" to resultAvailableDishUpdate,
                    "creationDate" to etCreationDateDishUpdate.text.toString(),
                    "price" to resultPriceDishUpdate
                )

                if (idDish != null) {
                    db.collection("Dish").document(idDish).update(data as Map<String, Any>).
                    addOnSuccessListener {
                        Toast.makeText(this, "Successfully updated dish", Toast.LENGTH_SHORT).show()
                        goActivity(DishMain::class.java)
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error updating a dish", Toast.LENGTH_SHORT).show()
                        goActivity(DishMain::class.java)
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