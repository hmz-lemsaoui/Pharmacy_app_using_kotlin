package com.example.e_commerceapp.Activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.Interface.OnNumbersNavigationChange
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), HomeFragment.OnItemHomeFragmentListener,
    OnNumbersNavigationChange {
    lateinit var binding : ActivityHomeBinding
    lateinit var manager: FragmentManager
    lateinit var trans: FragmentTransaction
    lateinit var managementCart: ManagementCart
    lateinit var managementFavorite: ManagementFavorite
    var cartCounter: Int? = 0
    var favoriteCounter: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        manager = supportFragmentManager
        // bottom navigation bar
        managementCart = ManagementCart(this)
        managementFavorite = ManagementFavorite(this)
        val homeBtn = binding.homeBtn
        val cartBtn = binding.cartBtn
        val profileBtn = binding.profilebtn
        val favoriteBtn = binding.favoriteBtn
        val supportBtn = binding.supportBtn
        homeBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val homeFragment = HomeFragment()
            trans.replace(binding.mainContentFragment.id,homeFragment)
            trans.commit()
        }
        cartBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val cartFragment = CartFragment()
            trans.replace(binding.mainContentFragment.id,cartFragment)
            trans.commit()
        }
        profileBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val userProfileFragment = UserProfileFragment()
            trans.replace(binding.mainContentFragment.id,userProfileFragment)
            trans.commit()
        }
        favoriteBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val favoritesFragment = FavoritesFragment()
            trans.replace(binding.mainContentFragment.id,favoritesFragment)
            trans.commit()
        }
        supportBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val rateUsFragment = RateUsFragment()
            trans.replace(binding.mainContentFragment.id,rateUsFragment)
            trans.commit()
        }

        // home fragment
        trans = manager.beginTransaction()
        val homeFragment = HomeFragment()
        trans.replace(binding.mainContentFragment.id,homeFragment)
        trans.commit()
        // change numbers
        changeNumbers()

    }

    fun changeNumbers(){
        val item_count_card = binding.itemCountCard
        val item_count_favorite = binding.itemCountFavorite
        //counter of number of item in card
        cartCounter = managementCart.getNumberOfItemInCard()
        if (cartCounter == 0) {
            item_count_card.visibility = View.GONE
        }
        if (cartCounter!! >= 1) {
            item_count_card.visibility = View.VISIBLE
            item_count_card.text = cartCounter.toString()
        }
        //counter of number of item in favorite
        favoriteCounter = managementFavorite.getNumberOfItemInFavorite()
        if (favoriteCounter == 0) {
            item_count_favorite.visibility = View.GONE
        }
        if (favoriteCounter!! >= 1) {
            item_count_favorite.visibility = View.VISIBLE
            item_count_favorite.text = favoriteCounter.toString()
        }
    }

    override fun passMedcineInfo(recomendedDomain: RecomendedDomain) {
        trans = manager.beginTransaction()
        val showDetailFragment = ShowDetailFragment.newInstance(recomendedDomain)
        trans.replace(binding.mainContentFragment.id,showDetailFragment)
        trans.commit()
    }

    override fun seeAllMedcines(categoryItems: ArrayList<RecomendedDomain>) {
        trans = manager.beginTransaction()
        val produitsFragment = ProduitsFragment.newInstance(categoryItems)
        trans.replace(binding.mainContentFragment.id,produitsFragment)
        trans.commit()
    }

    override fun onNumbersChange() {
        changeNumbers()
    }
}