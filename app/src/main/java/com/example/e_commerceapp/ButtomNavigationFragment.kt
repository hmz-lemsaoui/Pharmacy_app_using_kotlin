package com.example.e_commerceapp



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.e_commerceapp.Activity.CartActivity
import com.example.e_commerceapp.Activity.FavoritesActivity
import com.example.e_commerceapp.Activity.HomeActivity
import com.example.e_commerceapp.Activity.UserProfileActivity
import com.example.e_commerceapp.Adapter.CartListAdapter
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.databinding.FragmentButtomNavigationBinding


class ButtomNavigationFragment() : Fragment() {
    lateinit var binding: FragmentButtomNavigationBinding
    lateinit var managementCart: ManagementCart
    lateinit var managementFavorite: ManagementFavorite
    var cartCounter: Int? = 0
    var favoriteCounter: Int?=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentButtomNavigationBinding.inflate(inflater, container, false)
        managementCart = ManagementCart(inflater.context)
        managementFavorite= ManagementFavorite(inflater.context)
        val homeBtn = binding.homeBtn
        val cartBtn = binding.cartBtn
        val item_count_card=binding.itemCountCard
        val item_count_favorite=binding.itemCountFavorite
        val profileBtn = binding.profilebtn
        val favoriteBtn=binding.favoriteBtn
        //counter of number of item in card
        cartCounter=managementCart.getNumberOfItemInCard()
        if(cartCounter==0){
            item_count_card.visibility=View.GONE
        }
        if(cartCounter!! >=1){
            item_count_card.visibility = View.VISIBLE
            item_count_card.text = cartCounter.toString()
        }
        //counter of number of item in favorite
        favoriteCounter=managementFavorite.getNumberOfItemInFavorite()
        if(favoriteCounter==0){
            item_count_favorite.visibility=View.GONE
        }
        if(favoriteCounter!! >=1){
            item_count_favorite.visibility = View.VISIBLE
            item_count_favorite.text = favoriteCounter.toString()
        }
        //

        homeBtn.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }
        cartBtn.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }
        profileBtn.setOnClickListener {
            startActivity(Intent(context, UserProfileActivity::class.java))
        }
        favoriteBtn.setOnClickListener {
            startActivity(Intent(context, FavoritesActivity::class.java))
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ButtomNavigationFragment()
    }
}