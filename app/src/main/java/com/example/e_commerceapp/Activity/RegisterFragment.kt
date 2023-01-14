package com.example.e_commerceapp.Activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.e_commerceapp.databinding.FragmentRegisterBinding
import com.example.e_commerceapp.Domain.User
import com.example.e_commerceapp.Healper.DbHelper


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val db = DbHelper(requireContext())
        var txterror = binding.texterror
        var name = binding.name.text.toString()
        var email = binding.email.text.toString()
        var password = binding.password.text.toString()
        var confPassword = binding.confirmation.text.toString()
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
            txterror.text = "⁉ You Must Fill All The Fields"
            txterror.visibility = View.VISIBLE
        } else if (password != confPassword) {
            txterror.text = "⁉ The Passwords Are Different"
            txterror.visibility = View.VISIBLE
        } else if (db.checkIfEmailExist(email)) {
            txterror.text = "⁉ User With This E-mail Already Exists"
            txterror.visibility = View.VISIBLE
        } else {
            val result = db.insertUser(User(name = name,email=email,password = password))
            if (!result){
                txterror.text = "⁉ Try Again !"
                txterror.visibility = View.VISIBLE
            }else{
                Toast.makeText(context, "successfully registered", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, HomeActivity::class.java)

                val sharedPref = requireContext().getSharedPreferences("userinfos", Context.MODE_PRIVATE).edit()
                sharedPref.apply{
                    //putInt("id", value.id)
                    putString("username", name)
                    putString("email", email)
                    putString("password", password)
                    //putInt("image", value.image)
                    apply()
                }
                startActivity(intent)
            }
        }
    }
}