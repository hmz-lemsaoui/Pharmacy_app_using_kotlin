package com.example.e_commerceapp.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.MyListener
import com.example.e_commerceapp.databinding.FragmentRegisterBinding
import com.example.e_commerceapp.models.User


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater , container , false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener {
            myListener.registerNewUser(User(name = binding.name.text.toString(), email = binding.email.text.toString(), password = binding.password.text.toString()),binding.confirmation.text.toString())
        }
    }

    lateinit var myListener: MyListener
    fun setOnButtonClickedListener(myListener : MyListener) {
        this.myListener = myListener
    }

}