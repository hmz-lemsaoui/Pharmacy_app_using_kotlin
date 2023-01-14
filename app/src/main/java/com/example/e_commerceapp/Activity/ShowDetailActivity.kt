package com.example.e_commerceapp.Activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityShowDetailBinding

class ShowDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowDetailBinding
    lateinit var managementCart: ManagementCart
    lateinit var managementFavorite: ManagementFavorite
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_detail)
        managementCart = ManagementCart(this)
        managementFavorite = ManagementFavorite(this)

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
        val favorite_btn=binding.favoriteBtn

        mediaPlayer = MediaPlayer.create(this , R.raw.add_to_card)

        var intFavorite= 0
        // recuperation du bundle
        val obj = intent.getSerializableExtra("object") as RecomendedDomain
        val drawableResourceId = this.resources.getIdentifier(obj.pic,"drawable",this.packageName)
        Glide.with(this).load(drawableResourceId).into(medicalPic)
        price_txt.text = "${obj.price}"
        titledetailtxt.text = obj.title
        descriptiontxt.text = obj.description
        numberItemtxt.text = obj.numberInCart.toString()
        startxt.text = obj.star.toString()
        timetxt.text = obj.time.toString()
        textVi_caloriestxt.text = obj.calories.toString()
        totalPricetxt.text = "${Math.round(obj.price * obj.numberInCart)}"

        var numberOrder = numberItemtxt.text.toString().toInt()

        addToCartBtn.setOnClickListener {
            obj.numberInCart = numberOrder
            managementCart.insertProduit(obj)
            mediaPlayer.start()
        }
        if(obj.isFavorite){
            favorite_btn.setImageResource(R.drawable.ic_favorite)
        }
        else{
            favorite_btn.setImageResource(R.drawable.ic_favorite_border)
        }
        favorite_btn.setOnClickListener{
            if(obj.isFavorite){
                favorite_btn.setImageResource(R.drawable.ic_favorite_border)
                obj.isFavorite=false
            }else{
                favorite_btn.setImageResource(R.drawable.ic_favorite)
                obj.isFavorite=true
            }
            managementFavorite.insertFavorite(obj)
        }
        minusCardbtn.setOnClickListener {
            if (numberOrder > 1){
                numberOrder -= 1
            }
            numberItemtxt.text = numberOrder.toString()
            totalPricetxt.text = "${Math.round(obj.price * numberOrder)}"
        }
        plusCardbtn.setOnClickListener {
            numberOrder += 1
            numberItemtxt.text = numberOrder.toString()
            totalPricetxt.text = "${Math.round(obj.price * numberOrder)}"
        }
    }
}