package com.example.e_commerceapp.Healper

import android.content.Context
import android.widget.Toast
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Interface.ChangeNumberItemListener

class ManagementFavorite (
    val mContext: Context
){
    val tinyDB = TinyDb(mContext)
    fun insertFavorite(item: RecomendedDomain){
        val listProduitsFavorite: ArrayList<RecomendedDomain> = getListFavorite()
        var existAlready = false
        var n = 0
        for (i in 0 until listProduitsFavorite.size){
            if (listProduitsFavorite[i].title == item.title){
                existAlready = true
                n = i
            }
        }
        if (!existAlready && item.isFavorite){
            listProduitsFavorite.add(item)
            Toast.makeText(mContext,"Added to your Favorite",Toast.LENGTH_SHORT).show()
        }
        else{
            listProduitsFavorite.removeAt(n)
            Toast.makeText(mContext,"Removed from your Favorite",Toast.LENGTH_SHORT).show()
        }
        tinyDB.putListObject("FavoriteList",listProduitsFavorite)

    }
    fun getListFavorite(): ArrayList<RecomendedDomain>{
        // return ArrayList()
        return tinyDB.getListObject("FavoriteList")
    }
    fun deleteElementBySwip(listProduitsFavorite: ArrayList<RecomendedDomain>,position: Int,changeNumberItemListener: ChangeNumberItemListener){
        listProduitsFavorite.removeAt(position)
        tinyDB.putListObject("FavoriteList",listProduitsFavorite)
        changeNumberItemListener.changed()
    }
    fun getNumberOfItemInFavorite():Int{
        return getListFavorite().size
    }

}