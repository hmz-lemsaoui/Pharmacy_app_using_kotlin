package com.example.e_commerceapp.Activity;


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerceapp.Adapter.RecomendedAdapter
import com.example.e_commerceapp.ButtomNavigationFragment
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityProduitsBinding

class ProduitsActivity : AppCompatActivity(){
    lateinit var binding : ActivityProduitsBinding
    lateinit var manager: FragmentManager
    lateinit var navigationFragment: ButtomNavigationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_produits)

        manager = supportFragmentManager
        val trans = manager.beginTransaction()
        navigationFragment = ButtomNavigationFragment()
        trans.replace(binding.fragmentContainerView.id,navigationFragment)
        trans.commit()

        recyclerViewPopular()
        binding.back2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }

    private fun recyclerViewPopular(){
        val charsearch1 = binding.charsearch1
        val manager =  GridLayoutManager(this, 3)
        binding.recyclerPopularList.layoutManager = manager
        val data = intent.extras?.getSerializable("data") as ArrayList<RecomendedDomain>
//        val data = listOf(
//            RecomendedDomain("test1","vitamin1","text dor description",13.0,5,20,100,1),
//            RecomendedDomain("test2","image1","text dor description",12.0,5,40,300,1),
//            RecomendedDomain("test3","vitamin2","text dor description",19.0,3,60,1100,1),
//            RecomendedDomain("test4","image2","text dor description",12.0,1,20,1020,1),
//            RecomendedDomain("test5","vitamin3","text dor description",13.4,5,10,1025,1),
//            RecomendedDomain("test6","image3","text dor description",16.4,4,14,2000,1),
//            RecomendedDomain("test7","ic_image7","text dor description",20.9,3,80,5000,1),
//            RecomendedDomain("test1","ic_image8","text dor description",13.0,5,20,100,1),
//            RecomendedDomain("test2","ic_image9","text dor description",12.0,5,40,300,1),
//            RecomendedDomain("test3","ic_image10","text dor description",19.0,3,60,1100,1),
//            RecomendedDomain("test4","ic_image11","text dor description",12.0,1,20,1020,1),
//            RecomendedDomain("test5","ic_image1","text dor description",13.4,5,10,1025,1),
//            RecomendedDomain("test6","ic_image2","text dor description",16.4,4,14,2000,1),
//            RecomendedDomain("test7","ic_image4","text dor description",20.9,3,80,5000,1)
//        )
        val adapter = RecomendedAdapter(data, this)
        binding.recyclerPopularList.adapter = adapter
        binding.imgeSearch.setOnClickListener {
            val searchValue = charsearch1.text.toString()
            adapter.filter.filter(searchValue)
        }

    }
    override fun onRestart() {
        navigationFragment.changeNumbers()
        super.onRestart()
    }
}
