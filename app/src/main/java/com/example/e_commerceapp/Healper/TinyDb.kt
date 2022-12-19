package com.example.e_commerceapp.Healper

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.google.gson.Gson
import java.util.*

class TinyDb(mContext: Context) {

    var preferences: SharedPreferences = mContext.getSharedPreferences("name", Context.MODE_PRIVATE)


    private fun putListString(key: String, stringList: ArrayList<String>) {
        val myStringList = stringList.toTypedArray()
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply()
    }

    fun putListObject(key: String, listProducts: ArrayList<RecomendedDomain>) {
        val gson = Gson()
        val objStrings = ArrayList<String>()
        listProducts.forEach {
            objStrings.add(gson.toJson(it))
        }
        putListString(key, objStrings)
    }
    fun getListObject(key: String): ArrayList<RecomendedDomain> {
        val gson = Gson()
        val objStrings = getListString(key)
        var listProducts = ArrayList<RecomendedDomain>()
        objStrings.forEach {
            val product: RecomendedDomain = gson.fromJson(it, RecomendedDomain::class.java)
            listProducts.add(product)
        }
        return listProducts
    }

    private fun getListString(key: String): ArrayList<String> {
        return ArrayList(Arrays.asList(*TextUtils.split(preferences.getString(key, ""), "‚‗‚")))
    }
}