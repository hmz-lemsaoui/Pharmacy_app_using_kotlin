package com.example.e_commerceapp.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentRateUsBinding

class RateUsFragment : Fragment() {
    lateinit var binding: FragmentRateUsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRateUsBinding.inflate(inflater , container, false)

        val rating_bar=binding.ratingBar
        rating_bar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            // Do something with the rating value
            Toast.makeText(context, "Rating: $rating", Toast.LENGTH_SHORT).show()
            val img:Int = when (rating.toInt()){
                1 -> R.drawable.emoji1
                2 -> R.drawable.emoji2
                3 -> R.drawable.emoji3
                4 -> R.drawable.emoji4
                else -> R.drawable.emoji5
            }
            binding.imageRate.setImageResource(img)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RateUsFragment()
    }
}