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
    lateinit var dataRecAdapter:ArrayList<RecomendedDomain>
    private val db = DbHelper(this)
    lateinit var recomendedAdapter: RecomendedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        recyclerViewPopular()
        recyclerViewCategory()
        binding.seeAll.setOnClickListener{
            val intent= Intent(this@HomeActivity, ProduitsActivity::class.java)
            val bundle=Bundle()
            bundle.putSerializable("data",dataRecAdapter)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        //afficher le nom de user

        val sharedPref = getSharedPreferences("userinfos", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username","username")
        val userimage = sharedPref.getInt("image",R.drawable.profile)
        binding.HiName.text="Hi $username"
        binding.imageProfile.setImageResource(userimage)

    }
    private fun recyclerViewPopular(){
        val charsearch1 = binding.charsearch1
        val manager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerPopularList.layoutManager = manager
        dataRecAdapter = db.getAllMedsByCategory("Sachet") // recuperation des mediacaments by category
        recomendedAdapter = RecomendedAdapter(dataRecAdapter,this)
        binding.recyclerPopularList.adapter = recomendedAdapter
        binding.imgeSearch.setOnClickListener {
            val searchValue = charsearch1.text.toString()
            recomendedAdapter.filter.filter(searchValue)
        }

    }

    private fun recyclerViewCategory(){
        val manager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclreCategorieList.layoutManager = manager
        val data = listOf(
            CategoryDomain("Sachet","sachet"),
            CategoryDomain("Sirop","sirop"),
            CategoryDomain("Gélule","capsule"),
            CategoryDomain("Comprimé","ic_image8"),
            CategoryDomain("Vitamin","ic_image11"),
            CategoryDomain("Ovule!Caps","ic_image3"),
        )
        // ici on passe le manger de recycler view pour qu'on puise
        // recupere le view attribue a une position bien determine
        val adapter = CategoryAdapter(data,manager,recomendedAdapter,dataRecAdapter,this)
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