package com.example.exameniib_zapata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib_zapata.R
import com.example.exameniib_zapata.Restaurant
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantAdapter(private val restaurantList: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class RestaurantViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nameRestaurant: TextView = itemView.findViewById(R.id.tvTitleNameRestaurantContent)
        val ratingRestaurant: TextView = itemView.findViewById(R.id.tvTitleRatingContent)
        val dateCreationRestaurant: TextView = itemView.findViewById(R.id.tvTitleOpeningDateContent)
        val availableRestaurant : TextView = itemView.findViewById(R.id.tvTitleAvailableContent)
        val btnDeleteRestaurant : Button = itemView.findViewById(R.id.btnRestaurantDelete)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view_restaurant,
            parent, false)
        return  RestaurantViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int = restaurantList.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = restaurantList[position]
        holder.nameRestaurant.text = restaurantList[position].name
        holder.ratingRestaurant.text = restaurantList[position].rating.toString()
        holder.dateCreationRestaurant.text = restaurantList[position].openingDate
        holder.availableRestaurant.text = restaurantList[position].available.toString()

        holder.btnDeleteRestaurant.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete")
            builder.setMessage("Are you sure to delete this restaurant?")
            builder.setPositiveButton("Yes"){ dialogInterface, i: Int ->
                val deleteItem = db.collection("Restaurant").document(item.id)
                println(item.id)
                db.runBatch {batch ->
                    batch.delete(deleteItem)
                }.addOnCompleteListener {
                    Toast.makeText(activity, "Successfully deleted restaurant", Toast.LENGTH_SHORT).show()
                    restaurantList.removeAt(position)
                    notifyDataSetChanged()
                }
            }
            builder.setNegativeButton("No"){ dialogInterface, i: Int ->
                println("No selected")
            }
            builder.show()
        }

    }
}




