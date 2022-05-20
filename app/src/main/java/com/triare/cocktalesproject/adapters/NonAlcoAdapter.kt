package com.triare.cocktalesproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.R
import com.triare.cocktalesproject.databinding.ItemAlcoholBinding
import com.triare.cocktalesproject.dvo.NonAlcoDvo

class NonAlcoAdapter() : ListAdapter<NonAlcoDvo, NonAlcoAdapter.NonAlcoHolder>(object : DiffUtil.ItemCallback<NonAlcoDvo>() {
        override fun areItemsTheSame(
            oldItem: NonAlcoDvo,
            newItem: NonAlcoDvo
        ): Boolean = oldItem.idDrink == newItem.idDrink

        override fun areContentsTheSame(
            oldItem: NonAlcoDvo,
            newItem: NonAlcoDvo
        ): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NonAlcoHolder {
        return NonAlcoHolder(ItemAlcoholBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NonAlcoHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }

    inner class NonAlcoHolder(binding: ItemAlcoholBinding) : RecyclerView.ViewHolder(binding.root) {
        private val descriptionDrink: TextView = binding.descriptionRecycle
        private val drinkImageView: ImageView = binding.imageAlcoholRecycle

        fun bind(item: NonAlcoDvo, context: Context) {
            descriptionDrink.text = item.drink
            Glide.with(context)
                .load(item.drinkImage)
                .error(R.drawable.ic_launcher_background)
                .into(drinkImageView)
//            itemView.setOnClickListener {
//                OnItemClick.onClick(item.idDrink) }
        }
    }
}
//     private val onItemClick: OnItemClick