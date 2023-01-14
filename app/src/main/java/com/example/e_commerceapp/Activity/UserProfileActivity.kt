package com.example.e_commerceapp.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.e_commerceapp.ButtomNavigationFragment
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    lateinit var manager: FragmentManager
    lateinit var navigationFragment: ButtomNavigationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)

        manager = supportFragmentManager
        val trans = manager.beginTransaction()
        navigationFragment = ButtomNavigationFragment()
        trans.replace(binding.fragmentContainerView2.id,navigationFragment)
        trans.commit()

        val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE)
        val userId=sharedPref.getInt("id",-1)
        val username = sharedPref.getString("username","username")
        val email =sharedPref.getString("email","test@demo.com")
        val password=sharedPref.getString("password","******")

        val mobile=sharedPref.getString("mobile","")
        val adress=sharedPref.getString("adress","")
        val code=sharedPref.getString("code","")

//        imageView = binding.imageprofile
        binding.username.text=username
        binding.email.text=email
        binding.mobile.setText(mobile)
        binding.adress.setText(adress)
        binding.code.setText(code)

        binding.EditProfileBtn.setOnClickListener{
            if (binding.password.text.toString()!="" && binding.mobile.text.toString()!="" && binding.adress.text.toString()!="" && binding.code.text.toString()!="" ){
                if (binding.password.text.toString() == password){
                    DbHelper(this).updateUser(this,userId,binding.mobile.text.toString(),binding.adress.text.toString(),binding.code.text.toString())
                    val sharedPrefedit = getSharedPreferences("userinfos", MODE_PRIVATE).edit()
                    sharedPrefedit.putString("mobile", binding.mobile.text.toString())
                    sharedPrefedit.putString("adress", binding.adress.text.toString())
                    sharedPrefedit.putString("code", binding.code.text.toString())
                    sharedPrefedit.apply()

                    Toast.makeText(applicationContext, "Your Info's Has been Modified Successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Your Password Is Incorrect", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Please Fill All Failed", Toast.LENGTH_SHORT).show()
            }

            val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE)
            val mobile=sharedPref.getString("mobile","")
            val adress=sharedPref.getString("adress","")
            val code=sharedPref.getString("code","")

            binding.password.setText("")
            binding.mobile.setText(mobile)
            binding.adress.setText(adress)
            binding.code.setText(code)
        }

    }
    override fun onRestart() {
        navigationFragment.changeNumbers()
        super.onRestart()
    }

}

