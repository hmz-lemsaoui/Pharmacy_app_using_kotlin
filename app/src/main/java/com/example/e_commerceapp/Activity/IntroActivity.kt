package com.example.e_commerceapp.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.ButtomNavigationFragment
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIntroBinding =DataBindingUtil.setContentView(this,
            R.layout.activity_intro
        )

        binding.startbtn.setOnClickListener {
            val sharedPref=getSharedPreferences("userinfos", Context.MODE_PRIVATE)
            val id=sharedPref.getInt("id",-1)
            val username=sharedPref.getString("username",null)
            val email=sharedPref.getString("email",null)
            val image=sharedPref.getInt("image",-1)
            if(id!=-1||username!=null || email!=null || image!=-1){
                startActivity(Intent(this@IntroActivity, HomeActivity::class.java))
            }else{
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            }
        }

    }
}