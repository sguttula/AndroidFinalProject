package com.example.myfinalproject


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myfinalproject.databinding.FragmentSingUpBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass.
 */
class UserSignUpFragment : Fragment() {

    private  lateinit var binding: FragmentSingUpBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sing_up, container, false )
        binding.progressBar.setVisibility(View.GONE)

        binding.continueBtn.setOnClickListener {
            binding.progressBar.setVisibility(View.VISIBLE)
            signUp(binding.email.text.toString(), binding.newPass.text.toString(), it)
        }

        return binding.root
    }

    private fun signUp(email:String, password: String, view: View)
    {
        db.collection("userType")
            .whereEqualTo("Email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var temp = document.get("type").toString()
                    if(temp.equals("user")){
                        binding.progressBar.setVisibility(View.GONE)
                        binding.errorTxt.setVisibility(View.VISIBLE)
                        binding.errorTxt.text = "You are already a user"
                    }else if(temp.equals("provider")){
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener{ task ->
                                if (task.isSuccessful) {
                                    view.findNavController().navigate(R.id.action_userSignUpFragment_to_userSignUpInfot)
                                }else{
                                    binding.progressBar.setVisibility(View.GONE)
                                    binding.errorTxt.text = "Wrong Password"
                                }
                            }

                    }else{
                        binding.progressBar.setVisibility(View.GONE)
                        binding.errorTxt.setVisibility(View.VISIBLE)
                        binding.errorTxt.text = "You are already a user"
                    }

                }
            }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view.findNavController().navigate(R.id.action_userSignUpFragment_to_userSignUpInfot)
                } else {
                    binding.progressBar.setVisibility(View.GONE)
                    binding.errorTxt.setVisibility(View.VISIBLE)
                }
            }


    }


}
