package com.example.e_commerceapp.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.Domain.User
import com.example.e_commerceapp.Healper.DbHelper

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            loginUser()
        }
    }
    private fun loginUser(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        var texterror = binding.txterror
        val db = DbHelper(requireContext())
        if (email.isEmpty() || password.isEmpty()){
            texterror.text = "⁉ You Must Fill All The Fields"
            texterror.visibility = View.VISIBLE
        }else{
            val user = db.verifyLogin(User(email = email,password = password))
            if (user == null){
                texterror.text = "⁉ Email Or Password Is Not Correct"
                texterror.visibility = View.VISIBLE
            }else{
                Toast.makeText(context, "successfully registered", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, HomeActivity::class.java)
                val sharedPref = requireContext().getSharedPreferences("userinfos", Context.MODE_PRIVATE).edit()
                sharedPref.apply {
                    putInt("id", user.id)
                    putString("username", user.name)
                    putString("email", user.email)
                    putString("password", user.password)
                    //putInt("image", value.image)
                    apply()
                }
                startActivity(intent)
            }
        }
    }
}