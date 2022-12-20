package com.example.e_commerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.Adapter.CartListAdapter
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_cart)
        managementCart = ManagementCart(this)
        initList()
        // buttomNavigation()
        calculateCard()
    }
    fun calculateCard(){
        val totalItemCard = binding.totalItemCard
        val delivryCard = binding.delivryCard
        val taxCard = binding.taxCard
        val totalCard = binding.totalCard
        val parencentTax = 0.02
        val delivery = 10.0
        var tax = Math.round(managementCart.getTotalFee() * parencentTax *100.0)/ 100.0
        var total = Math.round((managementCart.getTotalFee() + tax + delivery)*100.0) /100.0
        var totalItem = Math.round(managementCart.getTotalFee()*100.0)/100.0
        totalItemCard.text = "$$totalItem"
        delivryCard.text = "$$delivery"
        taxCard.text = "$$tax"
        totalCard.text = "$$total"
    }

//    fun buttomNavigation(){
//        val homeBtn = binding.homeBtn
//        val cartBtn = binding.cartBtn
//        homeBtn.setOnClickListener {
//            startActivity(Intent(this@CartActivity, MainActivity::class.java))
//        }
//        cartBtn.setOnClickListener {
//            startActivity(Intent(this@CartActivity, CartActivity::class.java))
//        }
//    }

    fun initList(){
        val manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewList.layoutManager = manager
        val data = managementCart.getListCart()
        val adapter = CartListAdapter(data,object : ChangeNumberItemListener{
            override fun changed() {
                calculateCard()
            }

        },this)
        binding.recyclerViewList.adapter = adapter
        val isEmptyCard = binding.isEmptyCard
        val scrollViewCardList = binding.scrollViewCardList
        if (managementCart.getListCart().isEmpty()){
            isEmptyCard.visibility = View.VISIBLE
            scrollViewCardList.visibility = View.GONE
        }else{
            isEmptyCard.visibility = View.GONE
            scrollViewCardList.visibility = View.VISIBLE
        }
    }
}