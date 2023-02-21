package com.kys.mvvmshoppinglistapp.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kys.mvvmshoppinglistapp.R
import com.kys.mvvmshoppinglistapp.data.db.entities.ShoppingItem
import com.kys.mvvmshoppinglistapp.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.tvName.text = curShoppingItem.name
        holder.tvAmount.text = "${curShoppingItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.ivMinus.setOnClickListener {
            if(curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return  items.size
    }


    inner class ShoppingViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        val ivPlus = itemView.findViewById<ImageView>(R.id.ivPlus)
        val ivMinus = itemView.findViewById<ImageView>(R.id.ivMinus)
    }

}