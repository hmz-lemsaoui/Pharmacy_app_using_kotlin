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
import com.example.e_commerceapp.Adapter.CartListAdapter
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Interface.ChangeNumberItemListener
import com.example.e_commerceapp.Interface.OnNumbersNavigationChange
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import java.math.BigDecimal


class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var config: PayPalConfiguration
    var amount: Double = 0.0
    lateinit var managementCart: ManagementCart
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
        binding = FragmentCartBinding.inflate(inflater, container, false)


        managementCart = ManagementCart(requireContext())

        config = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AWjX0hXuSE8MxglWSO9Cjm1K9iIqf-xI3e1PLzLLeuEUL-x_Nqnpr31Gg9CdV7m1S88S2iKKYUubA5l_")

        var intent = Intent(context, PayPalService::class.java)
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
//        startActivity(intent)
        binding.checkout.setOnClickListener {
            amount = binding.totalCard.text.toString().toDouble()
            var payment = PayPalPayment(
                BigDecimal.valueOf(amount),"USD","Asafar Payment",
                PayPalPayment.PAYMENT_INTENT_SALE)

            var it = Intent(context, PaymentActivity::class.java)
            it.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
            it.putExtra(PaymentActivity.EXTRA_PAYMENT,payment)
            startActivityForResult(it,123)

        }

        initList()
        calculateCard()


        return binding.root
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
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewList.layoutManager = manager
        val data = managementCart.getListCart()
        val adapter = CartListAdapter(data,object : ChangeNumberItemListener {
            override fun changed() {
                // increase the cart items number
                onNumbersNavigationChange?.onNumbersChange()

                calculateCard()
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
                managementCart.deleteElementBySwip(data,position,object : ChangeNumberItemListener {
                    override fun changed() {
                        calculateCard()
                        testVisible()
                        // increase the cart items number
                        onNumbersNavigationChange?.onNumbersChange()
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
}