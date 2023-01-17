package com.example.e_commerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.Publication
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemPublicationBinding

class PublicationAdapter(
    private val publications: List<Publication>,
    val mCon: Context
    ) : RecyclerView.Adapter<PublicationAdapter.MyViewHolder>() {
    lateinit var binding: ItemPublicationBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationAdapter.MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_publication,parent,false)
        binding = ItemPublicationBinding.bind(layout)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = publications[position]
        holder.titledatePublication.text=data.title
        holder.datePublication.text=data.date

        val mContext = holder.itemView.context

        val drawableResourceId = mContext.resources.getIdentifier(data.pic,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.imageOfPublication)
    }

    override fun getItemCount() = publications.size

    inner class MyViewHolder(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView) {
        var titledatePublication = binding.titledatePublication
        var datePublication=binding.datePublication
        var imageOfPublication = binding.imageOfPublication
    }
}
