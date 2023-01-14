package com.example.e_commerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityRateUsBinding

class RateUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_rate_us)
        val binding:ActivityRateUsBinding=DataBindingUtil.setContentView(this,R.layout.activity_rate_us)
        val rating_bar=binding.ratingBar
        rating_bar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            // Do something with the rating value
            Toast.makeText(this, "Rating: $rating", Toast.LENGTH_SHORT).show()
            val img:Int = when (rating.toInt()){
                1 -> R.drawable.emoji1
                2 -> R.drawable.emoji2
                3 -> R.drawable.emoji3
                4 -> R.drawable.emoji4
                else -> R.drawable.emoji5
            }
            binding.imageRate.setImageResource(img)
    }
}
}