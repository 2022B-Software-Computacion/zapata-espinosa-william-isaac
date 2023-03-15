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
import com.example.exameniib_zapata.Dish
import com.example.exameniib_zapata.R
import com.google.firebase.firestore.FirebaseFirestore

class DishAdapter(private val dishList: ArrayList<Dish>) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    private lateinit var mListener: DishAdapter.onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class DishViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nameDish: TextView = itemView.findViewById(R.id.tvTitleNameDishContent)
        val priceDish: TextView = itemView.findViewById(R.id.tvTitlePriceContent)
        val dateCreationDish: TextView = itemView.findViewById(R.id.tvTitleCreationDateContent)
        val availableDish : TextView = itemView.findViewById(R.id.tvTitleAvailableDishContent)
        val btnDeleteDish : Button = itemView.findViewById(R.id.btnDishDelete)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DishAdapter.DishViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view_dishes,
            parent, false)
        return DishAdapter.DishViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int = dishList.size

    override fun onBindViewHolder(holder: DishAdapter.DishViewHolder, position: Int) {
        val item = dishList[position]
        holder.nameDish.text = dishList[position].name
        holder.priceDish.text = dishList[position].price.toString()
        holder.dateCreationDish.text = dishList[position].creationDate
        holder.availableDish.text = dishList[position].available.toString()

        holder.btnDeleteDish.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete")
            builder.setMessage("Are you sure to delete this dish?")
            builder.setPositiveButton("Yes"){ dialogInterface, i: Int ->
                val deleteItem = db.collection("Dish").document(item.id)
                println(item.id)
                db.runBatch {batch ->
                    batch.delete(deleteItem)
                }.addOnCompleteListener {
                    Toast.makeText(activity, "Successfully deleted dish", Toast.LENGTH_SHORT).show()
                    dishList.removeAt(position)
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