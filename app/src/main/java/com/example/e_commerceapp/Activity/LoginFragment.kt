package com.example.e_commerceapp.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.MyListener
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.models.User

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get()= _binding!!
    lateinit var myListener: MyListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater , container , false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            myListener.loginUser(User(email =  binding.email.text.toString(), password = binding.password.text.toString()))
        }
    }
    fun setOnButtonClickedListener(myListener : MyListener) {
        this.myListener = myListener
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }


}