package com.example.deberzapata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deberzapata.Distributor
import com.example.deberzapata.R

class DistributorAdapter(private val distributorList: List<Distributor>) : RecyclerView.Adapter<DistributorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistributorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DistributorViewHolder(layoutInflater.inflate(R.layout.item_gas, parent, false))
    }

    override fun getItemCount(): Int = distributorList.size

    override fun onBindViewHolder(holder: DistributorViewHolder, position: Int) {
        val item = distributorList[position]
        holder.render(item)
    }

}