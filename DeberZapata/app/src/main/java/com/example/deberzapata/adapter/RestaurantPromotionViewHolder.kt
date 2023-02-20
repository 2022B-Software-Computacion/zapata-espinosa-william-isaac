package com.example.deberzapata.adapter

import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deberzapata.R
import com.example.deberzapata.RestaurantPromotion

class RestaurantPromotionViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

    val namerestaurantPromotion = view.findViewById<TextView>(R.id.tvRestaurantName)
    val  typeFood = view.findViewById<TextView>(R.id.tvRestaurantType)
    val durationDelivery = view.findViewById<TextView>(R.id.tvRestaurantDuration)
    val ratingRestaurant = view.findViewById<TextView>(R.id.tvRestaurantRating)
    val logoRestaurant = view.findViewById<ImageView>(R.id.ivRestaurant)

    fun render(restaurantPromotionModel: RestaurantPromotion){
        namerestaurantPromotion.text = restaurantPromotionModel.nameRestaurant
        typeFood.text = restaurantPromotionModel.TypeFood
        durationDelivery.text = restaurantPromotionModel.duration
        ratingRestaurant.text = restaurantPromotionModel.rating.toString()
        Glide.with(logoRestaurant.context).load(restaurantPromotionModel.logo).into(logoRestaurant)
        view.setOnClickListener {
            Toast.makeText(view.context, "Aplicaste a la promoción de ${restaurantPromotionModel.nameRestaurant}", Toast.LENGTH_SHORT).show()
        }

    }
}