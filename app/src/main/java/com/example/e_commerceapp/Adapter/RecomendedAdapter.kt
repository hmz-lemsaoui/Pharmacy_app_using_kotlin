package com.example.e_commerceapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Activity.ShowDetailActivity
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemRecomendedBinding

class RecomendedAdapter(
    val popularList: List<RecomendedDomain>
) : RecyclerView.Adapter<RecomendedAdapter.MyViewHolder>(){
    lateinit var binding: ItemRecomendedBinding

    inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){

        val imageRecomended = binding.imageRecomended
        val titleRecomended = binding.titleRecomended
        val feeRecomended = binding.feeRecomended
        val btn_add_to_card = binding.btnAddToCard

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_recomended,parent,false)
        binding = ItemRecomendedBinding.bind(layout)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = popularList[position]
        holder.titleRecomended.text = data.title
        holder.feeRecomended.text = "$${data.fee.toString()}"

        val mContext = holder.itemView.context
        val drawableResourceId = mContext.resources.getIdentifier(data.pic,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.imageRecomended)

        holder.btn_add_to_card.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java)
            intent.putExtra("object",data)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularList.size
    }
}