package com.example.e_commerceapp.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.CategoryDomain
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    val categoryList: List<CategoryDomain>,
    val layoutManager: LinearLayoutManager,
    val recomendedAdapter: RecomendedAdapter,
    var dataOfRecAdapter: ArrayList<RecomendedDomain>,
    mContext: Context
) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    lateinit var binding: ItemCategoryBinding
    private val db = DbHelper(mContext)
    var list: ArrayList<Int> = ArrayList()
    init {
        list.add(0)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        binding = ItemCategoryBinding.bind(layout)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = categoryList[position]
        holder.textOfCategory.text = data.title
        val mContext = holder.itemView.context

        val drawableResourceId = mContext.resources.getIdentifier(data.pic,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.imageOfCategory)
        layoutManager.findViewByPosition(list[0])?.setBackgroundResource(R.drawable.backgound_category2)
        holder.itemView.setOnClickListener{
            if (!list.contains(position)){
                list.add(position)
                if (list.size == 2){
                    layoutManager.findViewByPosition(list[0])?.setBackgroundResource(R.drawable.backgound_category)
                    list.removeAt(0)
                }
                it.setBackgroundResource(R.drawable.backgound_category2)
                var data = db.getAllMedsByCategory(holder.textOfCategory.text.toString())
                dataOfRecAdapter.clear()
                data.forEach {
                    dataOfRecAdapter.add(it)
                }
                recomendedAdapter.notifyDataSetChanged()
            }
        }

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