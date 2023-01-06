package com.example.e_commerceapp.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var imageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)

        val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE)
        val userId=sharedPref.getInt("id",-1)
        val username = sharedPref.getString("username","username")
        val email =sharedPref.getString("email","test@demo.com")
        val password=sharedPref.getString("password","******")

        val mobile=sharedPref.getString("mobile","")
        val adress=sharedPref.getString("adress","")
        val code=sharedPref.getString("code","")
        var userimage=sharedPref.getInt("image",0)

        imageView = binding.imageprofile

        imageView.setImageResource(userimage)
        binding.username.text=username
        binding.email.text=email
        binding.mobile.setText(mobile)
        binding.adress.setText(adress)
        binding.code.setText(code)

        imageView.setOnClickListener {
            userimage=showImageSelectionMenu(imageView,userimage)
        }


        binding.EditProfileBtn.setOnClickListener{
            if (binding.password.text.toString()!="" && binding.mobile.text.toString()!="" && binding.adress.text.toString()!="" && binding.code.text.toString()!="" ){
                if (binding.password.text.toString() == password){
                    DbHelper(this).updateUser(this,userId,binding.mobile.text.toString(),binding.adress.text.toString(),binding.code.text.toString(),userimage)
                    val sharedPrefedit = getSharedPreferences("userinfos", MODE_PRIVATE).edit()
                    sharedPrefedit.putString("mobile", binding.mobile.text.toString())
                    sharedPrefedit.putString("adress", binding.adress.text.toString())
                    sharedPrefedit.putString("code", binding.code.text.toString())
                    sharedPrefedit.putInt("image", userimage)
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

    private fun showImageSelectionMenu(anchorView: View?,insial: Int):Int {
        val popupMenu = PopupMenu(this, anchorView)
        popupMenu.inflate(R.menu.menu_image)
        var selectedimage=insial
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.image_1 -> {
                    imageView.setImageResource(R.drawable.ic_image1)
                    selectedimage= R.drawable.ic_image1
                    true

                }
                R.id.image_2 -> {
                    imageView.setImageResource(R.drawable.ic_image6)
                    selectedimage =R.drawable.ic_image5
                    true
                }
                R.id.image_3 -> {
                    imageView.setImageResource(R.drawable.ic_image5)
                    selectedimage =R.drawable.ic_image5
                    true
                }
                R.id.image_4 -> {
                    imageView.setImageResource(R.drawable.profile)
                    selectedimage = R.drawable.profile
                    true
                }
                // Add cases for other images here
                else-> false
            }

        }
        popupMenu.show()
        return selectedimage
    }
}

