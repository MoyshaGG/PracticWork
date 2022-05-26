package com.triare.cocktalesproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.R
import com.triare.cocktalesproject.data.api.IngredientDvo

class IngredientAdapter(var context: Context) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>()
    {
        var dataList = emptyList<IngredientDvo>()

        internal fun setDataList(dataList:List<IngredientDvo>)
        {
            this.dataList = dataList
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var image: ImageView
            var desc: TextView
            init {
                image = itemView.findViewById(R.id.imageIngredientsRecycle)
                desc = itemView.findViewById(R.id.descriptionRecycleIngredients)
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): IngredientAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_details,parent,false)
            return ViewHolder(view)

        }

        override fun onBindViewHolder(holder: IngredientAdapter.ViewHolder, position: Int) {

                val data = dataList[position]
                holder.desc.text = data.name
                //holder.image.setImageResource(data.)
            Glide.with(context).load(data.picture).into(holder.image)


        }


        override fun getItemCount() = dataList.size
    }