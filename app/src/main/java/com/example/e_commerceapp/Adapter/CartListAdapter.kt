package com.example.e_commerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemCartBinding

class CartListAdapter(
    val listMedicalSelected: ArrayList<RecomendedDomain>,
    val changeNumberItemListener: ChangeNumberItemListener,
    mContext: Context
) : RecyclerView.Adapter<CartListAdapter.MyViewHolder>(){
    lateinit var binding: ItemCartBinding
    var managementCart = ManagementCart(mContext)


    inner class MyViewHolder(
        itemView: View
    ) :RecyclerView.ViewHolder(itemView) {
        val feeEachItem = binding.feeEachItem
        val totalEachItem = binding.totalEachItem
        val plusCardbtn = binding.plusCardbtn
        val minusCardbtn = binding.minusCardbtn
        val numberItemtxt = binding.numberItemtxt
        val title_cart = binding.titleCart
        val image_cart = binding.imageCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        binding = ItemCartBinding.bind(layout)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listMedicalSelected[position]
        holder.title_cart.text = data.title
        holder.feeEachItem.text = "${data.price}"
        holder.totalEachItem.text = "${Math.round(data.numberInCart * data.price)}"
        holder.numberItemtxt.text = "${data.numberInCart}"
        val mContext = holder.itemView.context
        val drawableResourceId = mContext.resources.getIdentifier(data.pic,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.image_cart)
        holder.plusCardbtn.setOnClickListener {
            managementCart.plusNumberProduct(listMedicalSelected,position,object:ChangeNumberItemListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemListener.changed()
                }
            })
        }
        holder.minusCardbtn.setOnClickListener {
            managementCart.minusNumberProduct(listMedicalSelected,position,object:ChangeNumberItemListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemListener.changed()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return listMedicalSelected.size
    }
}