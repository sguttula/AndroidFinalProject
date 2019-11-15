package com.example.myfinalproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myfinalproject.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)
        binding.progressBar.setVisibility(View.GONE)

        binding.resetBtn.setOnClickListener {
            binding.progressBar.setVisibility(View.VISIBLE)
            sendEmail(binding.resetEmail.text.toString())
        }



        return binding.root
    }

    private fun sendEmail(emailAddress:String){
        auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener { Task ->
            if(Task.isSuccessful){
                binding.progressBar.setVisibility(View.GONE)
                binding.resetErrorTxt.setVisibility(View.VISIBLE)
                binding.resetErrorTxt.text = "Check your email."
            }else{
                binding.progressBar.setVisibility(View.GONE)
                binding.resetErrorTxt.setVisibility(View.VISIBLE)
            }
        }
    }


}
