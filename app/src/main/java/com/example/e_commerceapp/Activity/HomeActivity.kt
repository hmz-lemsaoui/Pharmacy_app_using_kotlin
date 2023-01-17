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
            trans.add(binding.mainContentFragment.id,homeFragment)
            trans.addToBackStack(null)
            trans.commit()
        }
        cartBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val cartFragment = CartFragment()
            trans.add(binding.mainContentFragment.id,cartFragment)
            trans.addToBackStack(null)
            trans.commit()
        }
        profileBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val userProfileFragment = UserProfileFragment()
            trans.add(binding.mainContentFragment.id,userProfileFragment)
            trans.addToBackStack(null)
            trans.commit()
        }
        favoriteBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val favoritesFragment = FavoritesFragment()
            trans.add(binding.mainContentFragment.id,favoritesFragment)
            trans.addToBackStack(null)
            trans.commit()
        }
        supportBtn.setOnClickListener {
            trans = manager.beginTransaction()
            val rateUsFragment = RateUsFragment()
            trans.add(binding.mainContentFragment.id,rateUsFragment)
            trans.addToBackStack(null)
            trans.commit()
        }

        // home fragment
        trans = manager.beginTransaction()
        val homeFragment = HomeFragment()
        trans.add(binding.mainContentFragment.id,homeFragment)
        trans.addToBackStack(null)
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

    override fun onMenuItemClicked(view: View) {
            val popup = PopupMenu(this,view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_profile, popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.Edit -> {
                        trans = manager.beginTransaction()
                        val userProfileFragment = UserProfileFragment()
                        trans.add(binding.mainContentFragment.id,userProfileFragment)
                        trans.addToBackStack(null)
                        trans.commit()
                        true
                    }
                    R.id.logout -> {
                        // Execute the action for the favorite menu item here
                        Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        val sharedPref = getSharedPreferences("userinfos", MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.clear()
                        editor.apply()
                        finish()
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }

    }

    override fun passMedcineInfo(recomendedDomain: RecomendedDomain) {
        trans = manager.beginTransaction()
        val showDetailFragment = ShowDetailFragment.newInstance(recomendedDomain)
        trans.add(binding.mainContentFragment.id,showDetailFragment)
        trans.addToBackStack(null)
        trans.commit()
    }

    override fun seeAllMedcines(categoryItems: ArrayList<RecomendedDomain>) {
        trans = manager.beginTransaction()
        val produitsFragment = ProduitsFragment.newInstance(categoryItems)
        trans.add(binding.mainContentFragment.id,produitsFragment)
        trans.addToBackStack(null)
        trans.commit()
    }

    override fun onNumbersChange() {
        changeNumbers()
    }
}