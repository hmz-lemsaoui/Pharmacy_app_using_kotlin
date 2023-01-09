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
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    val favoriteList: ArrayList<RecomendedDomain>,
    val changeNumberItemListener: ChangeNumberItemListener,
    val mCon: Context
) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() ,Filterable{
    lateinit var binding: ItemFavoriteBinding
    var listProduitsFiltrer: List<RecomendedDomain> = ArrayList()
    var managementFavorite = ManagementFavorite(mCon)

    init {
        listProduitsFiltrer = favoriteList
    }

    inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){

        val imageFavorite = binding.imageFavorite
        val titleFavorite = binding.titleFavorite
        val description=binding.descriptionFavorite
        val star=binding.star
        val feeFavorite = binding.feeFavorite
        val btn_remove_favorite = binding.removeFavorite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite,parent,false)
        binding = ItemFavoriteBinding.bind(layout)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listProduitsFiltrer[position]
        holder.titleFavorite.text = data.title
        holder.description.text=data.description
        holder.star.text=data.star.toString()
        holder.feeFavorite.text = "${data.fee} MAD"

        val mContext = holder.itemView.context
        val drawableResourceId = mContext.resources.getIdentifier(data.pic,"drawable",mContext.packageName)
        Glide.with(mContext).load(drawableResourceId).into(holder.imageFavorite)

//        holder.btn_remove_favorite.setOnClickListener {
//            if(data.isFavorite){
//                holder.btn_remove_favorite.setImageResource(R.drawable.ic_favorite_border)
//            }else{
//                holder.btn_remove_favorite.setImageResource(R.drawable.ic_favorite)
//            }
//            managementFavorite.insertFavorite(listProduitsFiltrer[position])
//        }

        holder.titleFavorite.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java)
            intent.putExtra("object",data)
//            holder.itemView.context.startActivity(intent)

            //pour transition
            val options = ActivityOptions.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity?,
                    Pair.create(binding.titleFavorite, "titleTransition"),
                    Pair.create(binding.imageFavorite, "imageTransition"),
                    Pair.create(binding.descriptionFavorite, "descriptionTransition"),
                    Pair.create(binding.feeFavorite, "priceTransition"))
                    Pair.create(binding.star,"starTransition")
                    Pair.create(binding.removeFavorite,"favoriteTransition")

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
                    favoriteList
                } else {
                    var resultsProduits = ArrayList<RecomendedDomain>()
                    for (pro in favoriteList) {
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

