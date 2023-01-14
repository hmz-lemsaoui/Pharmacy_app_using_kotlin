package com.example.e_commerceapp.Domain

import com.example.e_commerceapp.Healper.ManagementFavorite
import java.io.Serializable

data class RecomendedDomain(
    val title: String,
    val pic: String,
    var category:String="Sachet",
    val description: String,
    val price: Double,
    val star: Int,
    val time: Int,
    val calories: Int,
    var numberInCart: Int=1,
    var isFavorite: Boolean = false,
    ): Serializable