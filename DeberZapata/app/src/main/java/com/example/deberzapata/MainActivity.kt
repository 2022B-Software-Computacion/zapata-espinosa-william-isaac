package com.example.deberzapata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deberzapata.adapter.RestaurantPromotionAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        val btnGas = findViewById<ImageView>(R.id.icClose)
        btnGas.setOnClickListener{
            Toast.makeText(btnGas.context, "Dirigiéndose a la segunda ventana", Toast.LENGTH_SHORT).show()
            val launch = Intent(this, GasPage::class.java)
            startActivity(launch)
            true
        }
    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPromotion)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RestaurantPromotionAdapter(RestaurantPromotionProvider.restaurantPromotionList)
    }
}