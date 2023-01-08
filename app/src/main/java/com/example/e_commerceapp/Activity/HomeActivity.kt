package com.example.e_commerceapp.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.Adapter.CategoryAdapter
import com.example.e_commerceapp.Adapter.RecomendedAdapter
import com.example.e_commerceapp.Domain.CategoryDomain
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var data:ArrayList<RecomendedDomain>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        recyclerViewCategory()
        recyclerViewPopular()
        binding.seeAll.setOnClickListener{
            val intent= Intent(this@HomeActivity, ProduitsActivity::class.java)
            val bundle=Bundle()
            bundle.putSerializable("data",data)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        //afficher le nom de user


        val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username","username")
        val userimage = sharedPref.getInt("image",R.drawable.profile)
        val id = sharedPref.getInt("id",-1)
        binding.HiName.text="Hi $username $id"
        binding.imageProfile.setImageResource(userimage)




    }
    private fun recyclerViewPopular(){
        val charsearch1 = binding.charsearch1
        val manager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerPopularList.layoutManager = manager
        val db = DbHelper(this)
        data = db.getAllMeds()
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
    fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_profile, popup.menu)
//        popup.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.Edit -> true
//                else -> false
//            }
//        }
        popup.show()
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.Edit -> {
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    // Execute the action for the favorite menu item here
                    Toast.makeText(applicationContext, "Log Out", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}