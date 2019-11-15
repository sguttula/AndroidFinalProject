package com.example.myfinalproject

import android.content.Intent
import com.example.myfinalproject.databinding.FragmentSignUpInfoBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass.
 */
class ProviderSignUpInfoSignUpInfot : Fragment() {

    private  lateinit var binding: FragmentSignUpInfoBinding
    // Access a Cloud Firestore instance from your Activity
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_info, container, false )
        binding.iconImg.setImageResource(R.drawable.provider_icon)
        binding.progressBar.setVisibility(View.GONE)

        binding.registerBtn.setOnClickListener {
            binding.progressBar.setVisibility(View.VISIBLE)
            //creates new user before adding to database
            val user = hashMapOf(
                "Name" to  binding.nameeditText.text.toString(),
                "Phone" to binding.phoneeditText.text.toString(),
                "Address" to binding.addresseditText.text.toString(),
                "City" to binding.cityeditText.text.toString(),
                "State" to binding.stateditText.text.toString(),
                "Zip" to binding.zipditText.text.toString()
            )
            register(user)
        }


        return binding.root
    }

    private fun register(newUser:HashMap<String,String>)
    {
        // Add a new document with a generated ID
        db.collection("Poviders")
            .add(newUser)
            .addOnSuccessListener {
                val intent = Intent (getActivity(), ProvidersMainActivity::class.java)
                getActivity()?.startActivity(intent)
            }
            .addOnFailureListener {
                binding.progressBar.setVisibility(View.GONE)
                binding.errorTxt.setVisibility(View.VISIBLE)
            }
    }


}