package com.example.exameniib_zapata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRestaurant = findViewById<Button>(R.id.btnRestaurant)
        btnRestaurant.setOnClickListener{
            goActivity(RestaurantMain::class.java)
        }

        val btnDish = findViewById<Button>(R.id.btnDish)
        btnDish.setOnClickListener{
            goActivity(DishMain::class.java)
        }
    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}