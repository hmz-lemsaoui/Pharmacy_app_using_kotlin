package com.example.e_commerceapp.Domain


import com.example.e_commerceapp.R
import java.io.Serializable

// model class
data class User(
    var id: Int = 0,
    var name: String? = "Default Name",
    var email: String?,
    var password: String?,
    var mobile: String? = "",
    var adress: String? = "",
    var PostalCode: String? = "",
    var image: Int = R.drawable.profile,
) : Serializable