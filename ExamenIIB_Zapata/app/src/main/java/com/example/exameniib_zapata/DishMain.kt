package com.example.exameniib_zapata

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib_zapata.adapter.DishAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DishMain : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dishList: ArrayList<Dish>
    private var db = Firebase.firestore
    var idDish = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_main)

        recyclerView = findViewById(R.id.recyclerDishes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dishList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Dish").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (document in it.documents) {
                    val dishItem: Dish? = document.toObject(Dish::class.java)
                    if (dishItem != null) {
                        dishItem.id = document.id
                        println(dishItem.id)
                        dishList.add(dishItem)
                    }
                }
                val adapter = DishAdapter(dishList)
                recyclerView.adapter =  adapter
                adapter.setOnItemClickListener(object : DishAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        //Toast.makeText(this@RestaurantMain, "Test $position", Toast.LENGTH_SHORT).show()
                        val idDish = dishList[position].id
                        Toast.makeText(this@DishMain, "Id:   $idDish", Toast.LENGTH_SHORT).show()
                        val btnEditDish = findViewById<Button>(R.id.btnDishEdit)
                        btnEditDish.setOnClickListener {
                            sentDataToOtherActiity(UpdateDish::class.java, idDish)
                        }
                    }
                })
            }
        }

            .addOnFailureListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }

        val btnCreateDish = findViewById<Button>(R.id.btnDishCreate)
        btnCreateDish.setOnClickListener {
            goActivity(CreateDish::class.java)
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

