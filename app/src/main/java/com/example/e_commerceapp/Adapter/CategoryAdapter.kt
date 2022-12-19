package com.example.e_commerceapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.CategoryDomain
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    val categoryList: List<CategoryDomain>
) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    lateinit var binding: ItemCategoryBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        binding = ItemCategoryBinding.bind(layout)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = categoryList[position]
        holder.textOfCategory.text = data.title
        var pic_url = ""
        when(position){
            0 -> pic_url="ic_image1"
            1 -> pic_url="ic_image2"
            2 -> pic_url="ic_image3"
            3 -> pic_url="ic_image4"
            4 -> pic_url="ic_image5"
            5 -> pic_url="ic_image6"
            6 -> pic_url="ic_image7"
            7 -> pic_url="ic_image8"
            8 -> pic_url="ic_image9"
            9 -> pic_url="ic_image10"
            10 -> pic_url="ic_image11"
            11 -> pic_url="ic_image12"
        }
        val mContext = holder.itemView.context
        val drawableResourceId = mContext.resources.getIdentifier(pic_url,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.imageOfCategory)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    inner class MyViewHolder(
         itemView : View
    ) : RecyclerView.ViewHolder(itemView) {
        var textOfCategory = binding.textOfCategory
        var imageOfCategory = binding.imageOfCategory
    }
}