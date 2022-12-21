package com.example.e_commerceapp.Activity

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.ThemedSpinnerAdapter
import com.example.e_commerceapp.databinding.ActivityRegisterBinding
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.models.User
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        var helper = DbHelper(this)
        binding.register.setOnClickListener {
            if (binding.name.text.toString() != "" && binding.email.text.toString()!="" && binding.password.text.toString()!="" && binding.confirmation.text.toString()!=""){
                registerNewUser(User(name = binding.name.text.toString(), email = binding.email.text.toString(), password = binding.password.text.toString()),binding.confirmation.text.toString())
            }
            else{
                Toast.makeText(this, "Please Fill All Failds", Toast.LENGTH_SHORT).show()
            }
        }


        val intent = Intent(this, LoginActivity::class.java)
        binding.signin.setOnClickListener {
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun registerNewUser(value: User, repass: String) {

        if (value.password.equals(repass)) {
            DbHelper(this).insertUser(value)
//            userName = value.name.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userMail", value.email)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Passwords Do Not Match Each other", Toast.LENGTH_LONG).show()
        }

    }

}