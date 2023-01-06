package com.example.e_commerceapp.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.MyListener
import com.example.e_commerceapp.databinding.ActivityMainBinding
import com.example.e_commerceapp.models.User
import java.util.*

class MainActivity : AppCompatActivity(), MyListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager: ViewPager = binding.viewPager
        val pagerAdapter = AuthenticationPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())
        viewPager.adapter = pagerAdapter
    }
    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is LoginFragment) {
            fragment.setOnButtonClickedListener(this)
        } else if (fragment is RegisterFragment) {
            fragment.setOnButtonClickedListener(this)
        }
    }

    internal inner class AuthenticationPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(
        fm!!,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(i: Int): Fragment {
            return fragmentList[i]
        }


        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }
    }
    override fun loginUser(value: User) {
        val user =DbHelper(this).verifyLogin(value)
        if (user!=null) {
            val intent = Intent(this, HomeActivity::class.java)
            val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE).edit()
            sharedPref.putInt("id", user.id)
            sharedPref.putString("username", user.name)
            sharedPref.putString("email", user.email)
            sharedPref.putString("password", user.password)
            sharedPref.putInt("image", value.image)
            sharedPref.apply()
            startActivity(intent)

        } else {
            Toast.makeText(this, "Incorrect Login Or Password", Toast.LENGTH_SHORT).show()
        }
    }
    override fun registerNewUser(value: User, repass: String) {
        if (value.password.equals(repass)) {
            DbHelper(this).insertUser(value)
//            userName = value.name.toString()
            val intent = Intent(this, HomeActivity::class.java)

            val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE).edit()
            sharedPref.putInt("id", value.id)
            sharedPref.putString("username", value.name)
            sharedPref.putString("email", value.email)
            sharedPref.putString("password", value.password)
            sharedPref.putInt("image", value.image)
            sharedPref.apply()

            startActivity(intent)
        } else {
            Toast.makeText(this, "Passwords Do Not Match Each other", Toast.LENGTH_LONG).show()
        }

    }
}