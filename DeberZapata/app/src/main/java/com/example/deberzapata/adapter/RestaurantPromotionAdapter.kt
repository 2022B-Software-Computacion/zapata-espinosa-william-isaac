package com.example.deberzapata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deberzapata.R
import com.example.deberzapata.RestaurantPromotion

class RestaurantPromotionAdapter(private val restaurantPromotionList: List<RestaurantPromotion>) : RecyclerView.Adapter<RestaurantPromotionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantPromotionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantPromotionViewHolder(layoutInflater.inflate(R.layout.item_promotion, parent, false))
    }

    override fun getItemCount() = restaurantPromotionList.size

    override fun onBindViewHolder(holder: RestaurantPromotionViewHolder, position: Int) {
        val item = restaurantPromotionList[position]
        holder.render(item)
    }
}