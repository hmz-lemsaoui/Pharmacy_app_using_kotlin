package com.example.e_commerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Adapter.CartListAdapter
import com.example.e_commerceapp.ButtomNavigationFragment
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagementCart
    lateinit var manager: FragmentManager
    lateinit var navigationFragment: ButtomNavigationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_cart)
        managementCart = ManagementCart(this)

        manager = supportFragmentManager
        val trans = manager.beginTransaction()
        navigationFragment = ButtomNavigationFragment()
        trans.replace(binding.fragmentContainerView.id,navigationFragment)
        trans.commit()

        initList()
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
        totalItemCard.text = "$totalItem"
        delivryCard.text = "$delivery"
        taxCard.text = "$tax"
        totalCard.text = "$total"
    }
    fun initList(){
        val manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewList.layoutManager = manager
        val data = managementCart.getListCart()
        val adapter = CartListAdapter(data,object : ChangeNumberItemListener{
            override fun changed() {
                // increase the cart items number
                notifyFragmentNumbers()

                calculateCard()
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
                managementCart.deleteElementBySwip(data,position,object : ChangeNumberItemListener{
                    override fun changed() {
                        calculateCard()
                        testVisible()
                        // increase the cart items number
                        notifyFragmentNumbers()
                        adapter.notifyDataSetChanged()
                    }
                })
            }
        })
        // Attach the item touch helper to your RecyclerView
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewList)

        binding.recyclerViewList.adapter = adapter
        testVisible()
    }
    private fun notifyFragmentNumbers(){
        navigationFragment.changeNumbers()
    }

    private fun testVisible(){
        val isEmptyCard = binding.isEmptyCard

        if (managementCart.getListCart().isEmpty()){
            isEmptyCard.visibility = View.VISIBLE
            binding.recyclerViewList.visibility = View.GONE
            binding.checkout.visibility = View.GONE
            binding.facture.visibility = View.GONE
        }else{
            isEmptyCard.visibility = View.GONE
            binding.recyclerViewList.visibility = View.VISIBLE
            binding.checkout.visibility = View.VISIBLE
            binding.facture.visibility = View.VISIBLE
        }
    }
    override fun onRestart() {
        navigationFragment.changeNumbers()
        super.onRestart()
    }
}