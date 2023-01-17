package com.example.e_commerceapp.Activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.Adapter.CategoryAdapter
import com.example.e_commerceapp.Adapter.RecomendedAdapter
import com.example.e_commerceapp.Domain.CategoryDomain
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.Interface.OnCategoryItemClickListener
import com.example.e_commerceapp.Interface.OnItemClickListener
import com.example.e_commerceapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var dataRecAdapter:ArrayList<RecomendedDomain>
    private lateinit var db: DbHelper
    lateinit var recomendedAdapter: RecomendedAdapter
    var onItemHomeFragmentListener: OnItemHomeFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onItemHomeFragmentListener = context as OnItemHomeFragmentListener?
    }

    override fun onDetach() {
        super.onDetach()
        onItemHomeFragmentListener = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        db = DbHelper(requireContext())
        recyclerViewPopular()
        recyclerViewCategory()

        binding.seeAll.setOnClickListener{
            onItemHomeFragmentListener?.seeAllMedcines(dataRecAdapter)
        }
        //afficher le nom de user

        val sharedPref = requireContext().getSharedPreferences("userinfos", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username","username")
        binding.HiName.text="Hi $username 👋"

        return binding.root
    }

    private fun recyclerViewPopular(){
        val charsearch1 = binding.charsearch1
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerPopularList.layoutManager = manager
        dataRecAdapter = db.getAllMedsByCategory("Sachet") // recuperation des mediacaments by category
        recomendedAdapter = RecomendedAdapter(dataRecAdapter,requireContext(),
            object : OnItemClickListener {
                override fun onItemClick(recomendedDomain: RecomendedDomain) {
                    onItemHomeFragmentListener?.passMedcineInfo(recomendedDomain)

                //pour transition
//            val options = ActivityOptions.makeSceneTransitionAnimation(
//                    holder.itemView.context as Activity?,
//                    Pair.create(binding.titleRecomended, "titleTransition"),
//                    Pair.create(binding.imageRecomended, "imageTransition"),
//                    Pair.create(binding.feeRecomended, "priceTransition"))
//            holder.itemView.context.startActivity(intent,options.toBundle())
                }

            })
        binding.recyclerPopularList.adapter = recomendedAdapter
        binding.imgeSearch.setOnClickListener {
            val searchValue = charsearch1.text.toString()
            recomendedAdapter.filter.filter(searchValue)
        }

    }
    private fun recyclerViewCategory(){
        val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclreCategorieList.layoutManager = manager
        val data = listOf(
            CategoryDomain("Sachet","sachet"),
            CategoryDomain("Gélule","capsule1"),
            CategoryDomain("Sirop","sirop"),
            CategoryDomain("Comprimé","ic_image8"),
            CategoryDomain("Vitamin","ic_image11")
        )
        // ici on passe le manger de recycler view pour qu'on puise
        // recupere le view attribue a une position bien determine
        val adapter = CategoryAdapter(data,manager,recomendedAdapter,dataRecAdapter,requireContext(),
            object : OnCategoryItemClickListener {
                override fun onItemClickListener(categoryItems: ArrayList<RecomendedDomain>) {
                    dataRecAdapter = categoryItems
                }

            })
        binding.recyclreCategorieList.adapter = adapter
    }

    interface OnItemHomeFragmentListener {
        fun passMedcineInfo(recomendedDomain: RecomendedDomain)
        fun seeAllMedcines(categoryItems: ArrayList<RecomendedDomain>)
    }
}