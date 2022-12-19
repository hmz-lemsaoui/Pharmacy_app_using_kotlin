package com.example.e_commerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityShowDetailBinding

class ShowDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowDetailBinding
    lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_detail)
        managementCart = ManagementCart(this)

        val medicalPic = binding.medicalPic
        val price_txt = binding.priceTxt
        val numberItemtxt = binding.numberItemtxt
        val startxt = binding.startxt
        val timetxt = binding.timetxt
        val textVi_caloriestxt = binding.textViCaloriestxt
        val descriptiontxt = binding.descriptiontxt
        val totalPricetxt = binding.totalPricetxt
        val addToCartBtn = binding.addToCartBtn
        val minusCardbtn = binding.minusCardbtn
        val plusCardbtn = binding.plusCardbtn
        val titledetailtxt = binding.titledetailtxt
        // recuperation du bundle
        val obj = intent.getSerializableExtra("object") as RecomendedDomain
        val drawableResourceId = this.resources.getIdentifier(obj.pic,"drawable",this.packageName)
        Glide.with(this).load(drawableResourceId).into(medicalPic)
        price_txt.text = "$${obj.fee}"
        titledetailtxt.text = obj.title
        descriptiontxt.text = obj.description
        numberItemtxt.text = obj.numberInCart.toString()
        startxt.text = obj.star.toString()
        timetxt.text = obj.time.toString()
        textVi_caloriestxt.text = obj.calories.toString()
        totalPricetxt.text = "$${Math.round(obj.fee * obj.numberInCart)}"

        var numberOrder = numberItemtxt.text.toString().toInt()

        addToCartBtn.setOnClickListener {
            obj.numberInCart = numberOrder
            managementCart.insertProduit(obj)
        }
        minusCardbtn.setOnClickListener {
            if (numberOrder > 1){
                numberOrder -= 1
            }
            numberItemtxt.text = numberOrder.toString()
            totalPricetxt.text = "$${Math.round(obj.fee * numberOrder)}"
        }
        plusCardbtn.setOnClickListener {
            numberOrder += 1
            numberItemtxt.text = numberOrder.toString()
            totalPricetxt.text = "$${Math.round(obj.fee * numberOrder)}"
        }
    }
}