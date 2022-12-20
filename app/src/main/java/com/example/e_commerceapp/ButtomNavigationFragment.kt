package com.example.e_commerceapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.Activity.CartActivity
import com.example.e_commerceapp.Activity.MainActivity
import com.example.e_commerceapp.databinding.FragmentButtomNavigationBinding

class ButtomNavigationFragment : Fragment() {
    lateinit var binding: FragmentButtomNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentButtomNavigationBinding.inflate(inflater, container, false)
        val homeBtn = binding.homeBtn
        val cartBtn = binding.cartBtn
        homeBtn.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
        cartBtn.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ButtomNavigationFragment()
    }
}