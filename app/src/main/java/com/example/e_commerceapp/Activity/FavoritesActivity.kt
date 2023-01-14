package com.example.e_commerceapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Adapter.FavoriteAdapter
import com.example.e_commerceapp.ButtomNavigationFragment
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    lateinit var binding : ActivityFavoritesBinding
    lateinit var managementFavorite: ManagementFavorite
    lateinit var manager: FragmentManager
    lateinit var navigationFragment: ButtomNavigationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)
        managementFavorite = ManagementFavorite(this)
        recyclerViewFavorite()

        manager = supportFragmentManager
        val trans = manager.beginTransaction()
        navigationFragment = ButtomNavigationFragment()
        trans.replace(binding.fragmentContainerView2.id,navigationFragment)
        trans.commit()

        binding.back2.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun recyclerViewFavorite(){

        val manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerFavoriteList.layoutManager = manager
        val data = managementFavorite.getListFavorite()
        testVisible()
        val adapter = FavoriteAdapter(data,object : ChangeNumberItemListener {
            override fun changed() {
                testVisible()
            }
        },this)

        //swipt to remove item from recyclerview
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                managementFavorite.deleteElementBySwip(data,position,object : ChangeNumberItemListener{
                    override fun changed() {
                        testVisible()
                        // increase the cart items number
                        notifyFragmentNumbers()

                        adapter.notifyDataSetChanged()
                    }
                })
            }
        })
        // Attach the item touch helper to your RecyclerView
        itemTouchHelper.attachToRecyclerView(binding.recyclerFavoriteList)
        binding.recyclerFavoriteList.adapter = adapter

    }
    private fun notifyFragmentNumbers(){
        navigationFragment.changeNumbers()
    }
    private fun testVisible(){
        val isEmptyCard = binding.isEmptyCard
        val scrollViewCardList = binding.scrollViewFavoriteList
        if (managementFavorite.getListFavorite().isEmpty()){
            isEmptyCard.visibility = View.VISIBLE
            scrollViewCardList.visibility = View.GONE
        }else{
            isEmptyCard.visibility = View.GONE
            scrollViewCardList.visibility = View.VISIBLE
        }
    }
    override fun onRestart() {
        navigationFragment.changeNumbers()
        super.onRestart()
    }
}