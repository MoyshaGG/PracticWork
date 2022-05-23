package com.triare.cocktalesproject.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.DetailsActivity
import com.triare.cocktalesproject.R
import com.triare.cocktalesproject.databinding.ItemAlcoholBinding
import com.triare.cocktalesproject.dvo.AlcoDvo


//interface OnItemClick {
//     fun onClick(appId: Int)
//}

class AlcoAdapter() :
    ListAdapter<AlcoDvo, AlcoAdapter.AlcoHolder>(object : DiffUtil.ItemCallback<AlcoDvo>() {
        override fun areItemsTheSame(
            oldItem: AlcoDvo,
            newItem: AlcoDvo
        ): Boolean = oldItem.idDrink == newItem.idDrink

        override fun areContentsTheSame(
            oldItem: AlcoDvo,
            newItem: AlcoDvo
        ): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcoHolder {
        return AlcoHolder(
            ItemAlcoholBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlcoHolder, position: Int, ) {
        holder.bind(getItem(position), holder.itemView.context)
//        holder.itemView.setOnClickListener { v ->
//            val intent = Intent(v.context, DetailsActivity::class.java)
//
//            intent.putExtra("drinkId",item.drinkId)
//            v.context.startActivity(intent)
//
//        }
    }

    inner class AlcoHolder(binding: ItemAlcoholBinding) : RecyclerView.ViewHolder(binding.root) {
        private val descriptionDrink: TextView = binding.descriptionRecycle
        private val drinkImageView: ImageView = binding.imageAlcoholRecycle

        fun bind(item: AlcoDvo, context: Context) {
            descriptionDrink.text = item.drink
            Glide.with(context)
                .load(item.drinkImage)
                .error(R.drawable.ic_launcher_background)
                .into(drinkImageView)
            itemView.setOnClickListener { v ->
                val intent = Intent(v.context, DetailsActivity::class.java)

                intent.putExtra("idDrink",item.idDrink)
                intent.putExtra("drinkImage",item.drinkImage)
                intent.putExtra("drink",item.drink)

                v.context.startActivity(intent)
            }
        }

    }
}