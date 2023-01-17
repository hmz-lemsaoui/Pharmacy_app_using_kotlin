package com.example.e_commerceapp.Activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerceapp.Healper.DbHelper
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentHomeBinding
import com.example.e_commerceapp.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {
    lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater,container,false)

        val sharedPref = requireContext().getSharedPreferences("userinfos", Context.MODE_PRIVATE)
        val userId=sharedPref.getInt("id",-1)
        val username = sharedPref.getString("username","username")
        val email =sharedPref.getString("email","test@demo.com")
        val password=sharedPref.getString("password","******")

        val mobile=sharedPref.getString("mobile","")
        val adress=sharedPref.getString("adress","")
        val code=sharedPref.getString("code","")

//        imageView = binding.imageprofile
        binding.username.text=username
        binding.email.text=email
        binding.mobile.setText(mobile)
        binding.adress.setText(adress)
        binding.code.setText(code)

        binding.EditProfileBtn.setOnClickListener{
            if (binding.password.text.toString()!="" && binding.mobile.text.toString()!="" && binding.adress.text.toString()!="" && binding.code.text.toString()!="" ){
                if (binding.password.text.toString() == password){
                    DbHelper(requireContext()).updateUser(requireContext(),userId,binding.mobile.text.toString(),binding.adress.text.toString(),binding.code.text.toString())
                    val sharedPrefedit = requireContext().getSharedPreferences("userinfos",
                        AppCompatActivity.MODE_PRIVATE
                    ).edit()
                    sharedPrefedit.putString("mobile", binding.mobile.text.toString())
                    sharedPrefedit.putString("adress", binding.adress.text.toString())
                    sharedPrefedit.putString("code", binding.code.text.toString())
                    sharedPrefedit.apply()

                    Toast.makeText(context, "Your Info's Has been Modified Successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Your Password Is Incorrect", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context, "Please Fill All Failed", Toast.LENGTH_SHORT).show()
            }

            val sharedPref = requireContext().getSharedPreferences("userinfos", Context.MODE_PRIVATE)
            val mobile=sharedPref.getString("mobile","")
            val adress=sharedPref.getString("adress","")
            val code=sharedPref.getString("code","")

            binding.password.setText("")
            binding.mobile.setText(mobile)
            binding.adress.setText(adress)
            binding.code.setText(code)
        }

        return binding.root;
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserProfileFragment()
    }
}