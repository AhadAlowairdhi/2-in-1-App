package com.example.a2in1app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_num.view.*

class adapter(private val guesses: ArrayList<String>) : RecyclerView.Adapter<adapter.itemViewHolder>() {
    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_num
                , parent
                , false
            )
        )

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val Guess = guesses[position]
        holder.itemView.apply {
            tv2.text=Guess
        }
    }

    override fun getItemCount() : Int = guesses.size
}