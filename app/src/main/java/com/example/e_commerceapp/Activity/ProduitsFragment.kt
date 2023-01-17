package com.example.e_commerceapp.Activity


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerceapp.Adapter.RecomendedAdapter
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.Interface.OnItemClickListener
import com.example.e_commerceapp.databinding.FragmentProduitsBinding


class ProduitsFragment : Fragment() {
    lateinit var binding: FragmentProduitsBinding
    lateinit var categoryItems: ArrayList<RecomendedDomain>
    var onItemHomeFragmentListener: HomeFragment.OnItemHomeFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onItemHomeFragmentListener = context as HomeFragment.OnItemHomeFragmentListener?
    }

    override fun onDetach() {
        super.onDetach()
        onItemHomeFragmentListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryItems = arguments?.getSerializable("data") as ArrayList<RecomendedDomain>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProduitsBinding.inflate(inflater, container, false)

        recyclerViewPopular()
        binding.back2.setOnClickListener {
           // startActivity(Intent(context, HomeActivity::class.java))
        }

        return binding.root
    }

    private fun recyclerViewPopular(){
        val charsearch1 = binding.charsearch1
        val manager =  GridLayoutManager(activity, 3)
        binding.recyclerPopularList.layoutManager = manager
        val data = categoryItems
        val adapter = RecomendedAdapter(data, requireContext(), object : OnItemClickListener {
            override fun onItemClick(recomendedDomain: RecomendedDomain) {
                onItemHomeFragmentListener?.passMedcineInfo(recomendedDomain)
            }

        })
        binding.recyclerPopularList.adapter = adapter
        binding.imgeSearch.setOnClickListener {
            val searchValue = charsearch1.text.toString()
            adapter.filter.filter(searchValue)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(categoryItems: ArrayList<RecomendedDomain>) =
            ProduitsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("data", categoryItems)
                }
            }
    }
}