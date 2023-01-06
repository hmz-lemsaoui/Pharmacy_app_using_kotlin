package com.example.e_commerceapp.Healper

import android.content.Context
import android.widget.Toast
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Interface.ChangeNumberItemListener

class ManagementCart (
    val mContext: Context
    ){
    val tinyDB = TinyDb(mContext)
    fun insertProduit(item: RecomendedDomain){
        val listProduits: ArrayList<RecomendedDomain> = getListCart()
        var existAlready = false
        var n = 0
        for (i in 0 until listProduits.size){
            if (listProduits[i].title == item.title){
                existAlready = true
                n = i
            }
        }
        if (existAlready){
            listProduits[n].numberInCart = item.numberInCart
        }else
            listProduits.add(item)
        tinyDB.putListObject("CardList",listProduits)
        Toast.makeText(mContext,"Added to your Cart",Toast.LENGTH_SHORT).show()
    }
    fun getListCart(): ArrayList<RecomendedDomain>{
        // return ArrayList()
        return tinyDB.getListObject("CardList")
    }
    fun minusNumberProduct(listProduits: ArrayList<RecomendedDomain>,position: Int,changeNumberItemListener: ChangeNumberItemListener){
        if (listProduits[position].numberInCart == 1){
            listProduits.removeAt(position)
        } else
            listProduits[position].numberInCart -= 1
        tinyDB.putListObject("CardList",listProduits)
        changeNumberItemListener.changed()
    }

    fun plusNumberProduct(listProduits: ArrayList<RecomendedDomain>,position: Int,changeNumberItemListener: ChangeNumberItemListener){
        listProduits[position].numberInCart += 1
        tinyDB.putListObject("CardList",listProduits)
        changeNumberItemListener.changed()
    }
    fun getTotalFee() : Double{
        val listProduits: ArrayList<RecomendedDomain> = getListCart()
        var fee = 0.0
        for (i in 0 until listProduits.size){
            fee +=  listProduits[i].fee * listProduits[i].numberInCart
        }
        return fee
    }
}