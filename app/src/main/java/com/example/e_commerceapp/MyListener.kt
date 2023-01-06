package com.example.e_commerceapp

import com.example.e_commerceapp.models.User

interface MyListener {
    fun registerNewUser(value: User, repass: String)
    fun loginUser(value: User)
}
