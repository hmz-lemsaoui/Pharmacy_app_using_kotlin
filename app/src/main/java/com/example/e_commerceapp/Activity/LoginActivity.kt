package com.example.e_commerceapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.e_commerceapp.databinding.ActivityLoginBinding
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R
import com.example.e_commerceapp.models.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        var helper = DbHelper(this)
        binding.login.setOnClickListener {
            if (binding.email.text.toString() != "" && binding.password.text.toString()!=""){
                loginUser(User(email =  binding.email.text.toString(), password = binding.password.text.toString()))
            }
            else{
                Toast.makeText(this, "Please Fill All Failds", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun loginUser(value: User) {
        val user =DbHelper(this).verifyLogin(value)
        if (user!=null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", user.name)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Incorrect Login Or Password", Toast.LENGTH_SHORT).show()
        }
    }
}