package com.example.deberzapata.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deberzapata.Distributor
import com.example.deberzapata.databinding.ItemGasBinding

class DistributorViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemGasBinding.bind(view)

    fun render(distributorModel: Distributor) {
        binding.tvRestaurantNameGas.text = distributorModel.nameRestaurant
        binding.tvRestaurantTypeGas.text = distributorModel.TypeFood
        binding.tvRestaurantDurationGas.text = distributorModel.duration
        binding.tvRestaurantRatingGas.text = distributorModel.rating.toString()
        Glide.with(binding.ivRestaurantGas.context).load(distributorModel.logo).into(binding.ivRestaurantGas)
        binding.tvFreeShippingGas.text = distributorModel.freeShipping

        itemView.setOnClickListener {
            Toast.makeText(binding.ivRestaurantGas.context, "Comprar Gas a ${distributorModel.nameRestaurant}", Toast.LENGTH_SHORT).show()
        }
    }
}