package com.example.a2in1app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.char_item.view.*

class guessAdapter(private val Guesses: ArrayList<String>) : RecyclerView.Adapter<guessAdapter.itemViewHolder>() {
    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.char_item
                , parent
                , false
            )
        )

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val Guess = Guesses[position]
        holder.itemView.apply {
            tv2.text=Guess
                    if(Guess.startsWith("Found")){
                        tv2.setTextColor(Color.GREEN)
                    }
                    else if(Guess.startsWith("Wrong")){
                        tv2.setTextColor(Color.RED)
                    }
                    else{
                        tv2.setTextColor(Color.BLACK)
                    }
                }
            }

    override fun getItemCount() : Int = Guesses.size
}