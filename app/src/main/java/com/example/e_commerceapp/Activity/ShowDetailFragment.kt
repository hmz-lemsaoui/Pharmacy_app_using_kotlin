package com.example.e_commerceapp.Activity

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerceapp.Domain.RecomendedDomain
import com.example.e_commerceapp.Healper.ManagementCart
import com.example.e_commerceapp.Healper.ManagementFavorite
import com.example.e_commerceapp.Interface.OnNumbersNavigationChange
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentShowDetailFragmentBinding

class ShowDetailFragment : Fragment() {
    lateinit var binding: FragmentShowDetailFragmentBinding
    lateinit var managementCart: ManagementCart
    lateinit var managementFavorite: ManagementFavorite
    lateinit var mediaPlayer: MediaPlayer
    lateinit var recomendedDomain: RecomendedDomain
    private var onNumbersNavigationChange: OnNumbersNavigationChange? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNumbersNavigationChange = context as OnNumbersNavigationChange?
    }

    override fun onDetach() {
        super.onDetach()
        onNumbersNavigationChange = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recomendedDomain = arguments?.getSerializable("object") as RecomendedDomain
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowDetailFragmentBinding.inflate(inflater,container,false)

        managementCart = ManagementCart(requireContext())
        managementFavorite = ManagementFavorite(requireContext())

        val medicalPic = binding.medicalPic
        val price_txt = binding.priceTxt
        val numberItemtxt = binding.numberItemtxt
        val startxt = binding.startxt
        val timetxt = binding.timetxt
        val textVi_caloriestxt = binding.textViCaloriestxt
        val descriptiontxt = binding.descriptiontxt
        val totalPricetxt = binding.totalPricetxt
        val addToCartBtn = binding.addToCartBtn
        val minusCardbtn = binding.minusCardbtn
        val plusCardbtn = binding.plusCardbtn
        val titledetailtxt = binding.titledetailtxt
        val favorite_btn=binding.favoriteBtn

        mediaPlayer = MediaPlayer.create(context , R.raw.add_to_card)

        var intFavorite= 0
        // recuperation du bundle
        val obj = recomendedDomain
        val drawableResourceId = requireActivity().resources.getIdentifier(obj.pic,"drawable",requireContext().packageName)
        Glide.with(this).load(drawableResourceId).into(medicalPic)
        price_txt.text = "${obj.price}"
        titledetailtxt.text = obj.title
        descriptiontxt.text = obj.description
        numberItemtxt.text = obj.numberInCart.toString()
        startxt.text = obj.star.toString()
        timetxt.text = obj.time.toString()
        textVi_caloriestxt.text = obj.calories.toString()
        totalPricetxt.text = "${Math.round(obj.price * obj.numberInCart)}"

        var numberOrder = numberItemtxt.text.toString().toInt()

        addToCartBtn.setOnClickListener {
            obj.numberInCart = numberOrder
            managementCart.insertProduit(obj)
            // increase the cart items number
            onNumbersNavigationChange?.onNumbersChange()

            mediaPlayer.start()
        }
        if(obj.isFavorite){
            favorite_btn.setImageResource(R.drawable.ic_favorite)
        }
        else{
            favorite_btn.setImageResource(R.drawable.ic_favorite_border)
        }
        favorite_btn.setOnClickListener{
            if(obj.isFavorite){
                favorite_btn.setImageResource(R.drawable.ic_favorite_border)
                obj.isFavorite=false
            }else{
                favorite_btn.setImageResource(R.drawable.ic_favorite)
                obj.isFavorite=true
            }
            managementFavorite.insertFavorite(obj)
            // increase the cart items number
            onNumbersNavigationChange?.onNumbersChange()
        }
        minusCardbtn.setOnClickListener {
            if (numberOrder > 1){
                numberOrder -= 1
            }
            numberItemtxt.text = numberOrder.toString()
            totalPricetxt.text = "${Math.round(obj.price * numberOrder)}"
        }
        plusCardbtn.setOnClickListener {
            numberOrder += 1
            numberItemtxt.text = numberOrder.toString()
            totalPricetxt.text = "${Math.round(obj.price * numberOrder)}"
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(recomendedDomain: RecomendedDomain) =
            ShowDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("object", recomendedDomain)
                }
            }
    }
}