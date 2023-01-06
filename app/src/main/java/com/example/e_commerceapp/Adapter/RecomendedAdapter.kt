package com.example.e_commerceapp.Adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Activity.ShowDetailActivity
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemRecomendedBinding

class RecomendedAdapter(
    val popularList: List<RecomendedDomain>,
    val mCon: Context
) : RecyclerView.Adapter<RecomendedAdapter.MyViewHolder>() ,Filterable{
    lateinit var binding: ItemRecomendedBinding
    var listProduitsFiltrer: List<RecomendedDomain> = ArrayList()

    init {
        listProduitsFiltrer = popularList
    }
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
        val data = listProduitsFiltrer[position]
        holder.titleRecomended.text = data.title
        holder.feeRecomended.text = "$${data.fee}"

        val mContext = holder.itemView.context
        val drawableResourceId = mContext.resources.getIdentifier(data.pic,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.imageRecomended)

        holder.btn_add_to_card.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java)
            intent.putExtra("object",data)
//            holder.itemView.context.startActivity(intent)

            //pour transition
            val options = ActivityOptions.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity?,
                    Pair.create(binding.titleRecomended, "titleTransition"),
                    Pair.create(binding.imageRecomended, "imageTransition"),
                    Pair.create(binding.feeRecomended, "priceTransition"))
            holder.itemView.context.startActivity(intent,options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return listProduitsFiltrer.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var strSearch = p0.toString()
                listProduitsFiltrer = if (strSearch.isEmpty()) {
                    popularList
                } else {
                    var resultsProduits = ArrayList<RecomendedDomain>()
                    for (pro in popularList) {
                        if (pro.title.lowercase().contains(strSearch.lowercase())
                        ) {
                            resultsProduits.add(pro)
                        }
                    }
                    resultsProduits
                }
                var filterResults = FilterResults()
                filterResults.values = listProduitsFiltrer
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listProduitsFiltrer = p1?.values as List<RecomendedDomain>
                if (listProduitsFiltrer.isEmpty()){
                    Toast.makeText(mCon,"No Medical for $p0",Toast.LENGTH_SHORT).show()
                }
                notifyDataSetChanged()
            }

        }
    }
}