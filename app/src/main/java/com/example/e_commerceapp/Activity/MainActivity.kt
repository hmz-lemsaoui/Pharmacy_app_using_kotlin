package com.example.e_commerceapp.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewPager: ViewPager = binding.viewPager
        val pagerAdapter = AuthenticationPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(LoginFragment()) // add login fragment
        pagerAdapter.addFragment(RegisterFragment()) // add register fragment
        viewPager.adapter = pagerAdapter
    }

    inner class AuthenticationPagerAdapter(
        fm: FragmentManager
    ) : FragmentPagerAdapter(
        fm,
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
}