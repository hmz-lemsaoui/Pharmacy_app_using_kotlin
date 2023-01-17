package com.example.e_commerceapp.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Adapter.FavoriteAdapter
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.Interface.OnNumbersNavigationChange
import com.example.e_commerceapp.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {
    lateinit var managementFavorite: ManagementFavorite
    lateinit var binding: FragmentFavoritesBinding
    private var onNumbersNavigationChange: OnNumbersNavigationChange? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNumbersNavigationChange = context as OnNumbersNavigationChange?
    }

    override fun onDetach() {
        super.onDetach()
        onNumbersNavigationChange = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater , container, false)

        managementFavorite = ManagementFavorite(requireContext())
        recyclerViewFavorite()
        binding.back2.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }

        return binding.root
    }


    private fun recyclerViewFavorite(){

        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerFavoriteList.layoutManager = manager
        val data = managementFavorite.getListFavorite()
        testVisible()
        val adapter = FavoriteAdapter(data,object : ChangeNumberItemListener {
            override fun changed() {
                testVisible()
            }
        },requireContext())

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
                managementFavorite.deleteElementBySwip(data,position,object : ChangeNumberItemListener {
                    override fun changed() {
                        testVisible()
                        // increase the cart items number
                        onNumbersNavigationChange?.onNumbersChange()

                        adapter.notifyDataSetChanged()
                    }
                })
            }
        })
        // Attach the item touch helper to your RecyclerView
        itemTouchHelper.attachToRecyclerView(binding.recyclerFavoriteList)
        binding.recyclerFavoriteList.adapter = adapter

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
}