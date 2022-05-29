package com.triare.cocktalesproject.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.R
import com.triare.cocktalesproject.dvo.IngredientDvo

class IngredientAdapter(var context: Context) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {
    var dataList = emptyList<IngredientDvo>()

    @SuppressLint("NotifyDataSetChanged")
    internal fun setDataList(dataList: List<IngredientDvo>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imageIngredientsRecycle)
        var desc: TextView = itemView.findViewById(R.id.descriptionRecycleIngredients)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val data = dataList[position]
        holder.desc.text = data.name
        Glide.with(context).load(dataList[position].picture)
            .error(R.drawable.ic_launcher_background)
            .into(holder.image)
    }

    override fun getItemCount() = dataList.size
}