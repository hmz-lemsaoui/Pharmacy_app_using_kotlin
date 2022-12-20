package com.example.e_commerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.Adapter.CategoryAdapter
import com.example.e_commerceapp.Adapter.RecomendedAdapter
import com.example.e_commerceapp.Domain.CategoryDomain
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        recyclerViewCategory()
        recyclerViewPopular()
        // buttomNavigation()

    }
//    fun buttomNavigation(){
//        val homeBtn = binding.homeBtn
//        val cartBtn = binding.cartbtn
//        homeBtn.setOnClickListener {
//            startActivity(Intent(this@MainActivity, MainActivity::class.java))
//        }
//        cartBtn.setOnClickListener {
//            startActivity(Intent(this@MainActivity, CartActivity::class.java))
//        }
//    }

    private fun recyclerViewPopular(){
        val charsearch1 = binding.charsearch1
        val manager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerPopularList.layoutManager = manager
        val data = listOf(
            RecomendedDomain("test1","ic_image1","text dor description",13.0,5,20,100,1),
            RecomendedDomain("test2","ic_image2","text dor description",12.0,5,40,300,1),
            RecomendedDomain("test3","ic_image3","text dor description",19.0,3,60,1100,1),
            RecomendedDomain("test4","ic_image4","text dor description",12.0,1,20,1020,1),
            RecomendedDomain("test5","ic_image5","text dor description",13.4,5,10,1025,1),
            RecomendedDomain("test6","ic_image6","text dor description",16.4,4,14,2000,1),
            RecomendedDomain("test7","ic_image7","text dor description",20.9,3,80,5000,1)
        )

        val adapter = RecomendedAdapter(data,this)
        binding.recyclerPopularList.adapter = adapter
        binding.imgeSearch.setOnClickListener {
            val searchValue = charsearch1.text.toString()
            adapter.filter.filter(searchValue)
        }

    }

    private fun recyclerViewCategory(){
        val manager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclreCategorieList.layoutManager = manager
        val data = listOf(
            CategoryDomain("test","ic_image1"),
            CategoryDomain("test2","ic_image2"),
            CategoryDomain("test3","ic_image3"),
            CategoryDomain("test4","ic_image4"),
            CategoryDomain("test5","ic_image5"),
            CategoryDomain("test5","ic_image6"),
            CategoryDomain("test5","ic_image7"),
            CategoryDomain("test5","ic_image8"),
            CategoryDomain("test5","ic_image9"),
            CategoryDomain("test5","ic_image10"),
            CategoryDomain("test5","ic_image11")
        )
        val adapter = CategoryAdapter(data)
        binding.recyclreCategorieList.adapter = adapter
    }
}