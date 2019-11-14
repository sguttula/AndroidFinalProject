package com.example.myfinalproject


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myfinalproject.databinding.FragmentSingUpBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class UserSignUpFragment : Fragment() {

    private  lateinit var binding: FragmentSingUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sing_up, container, false )

        binding.continueBtn.setOnClickListener {
            val pref = "user"
            val myEmail = pref.plus("-").plus(binding.email.text)
            signUp(myEmail, binding.newPass.text.toString())
        }

        return binding.root
    }

    private fun signUp(email:String, password: String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent (getActivity(), MainActivity::class.java)
                    getActivity()?.startActivity(intent)
                } else {
                    binding.errorTxt.setVisibility(View.VISIBLE)
                }
            }
    }


}