package com.example.exameniib_zapata

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib_zapata.adapter.RestaurantAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RestaurantMain : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantlist : ArrayList<Restaurant>
    private var db = Firebase.firestore
    var idRestaurant = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_main)

        recyclerView = findViewById(R.id.recyclerRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(this)

        restaurantlist = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Restaurant").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (document in it.documents) {
                    val restaurantItem: Restaurant? = document.toObject(Restaurant::class.java)
                    if (restaurantItem != null) {
                        restaurantItem.id = document.id
                        println(restaurantItem.id)
                        restaurantlist.add(restaurantItem)
                    }
                }

                val adapter = RestaurantAdapter(restaurantlist)
                recyclerView.adapter =  adapter
                adapter.setOnItemClickListener(object : RestaurantAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        //Toast.makeText(this@RestaurantMain, "Test $position", Toast.LENGTH_SHORT).show()
                        val idRestaurant = restaurantlist[position].id
                        Toast.makeText(this@RestaurantMain, "Id:   $idRestaurant", Toast.LENGTH_SHORT).show()
                        val btnEditRestaurant = findViewById<Button>(R.id.btnRestaurantEdit)
                        btnEditRestaurant.setOnClickListener {
                            sentDataToOtherActiity(UpdateRestaurant::class.java, idRestaurant)
                        }
                    }
                })
            }
        }
            .addOnFailureListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }

        val btnCreateRestaurant = findViewById<Button>(R.id.btnRestaurantCreate)
        btnCreateRestaurant.setOnClickListener {
            goActivity(CreateRestaurant::class.java)
        }

    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    private fun sentDataToOtherActiity(activity: Class<*>, id: String) {
        val intent = Intent(this, activity)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}