package com.example.e_commerceapp.Domain

import java.io.Serializable

data class RecomendedDomain(
    val title: String,
    val pic: String,
    val description: String,
    val fee: Double,
    val star: Int,
    val time: Int,
    val calories: Int,
    var numberInCart: Int
    ): Serializable