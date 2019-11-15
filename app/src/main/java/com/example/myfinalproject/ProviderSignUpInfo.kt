package com.example.myfinalproject

import android.content.Intent
import com.example.myfinalproject.databinding.FragmentSignUpInfoBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass.
 */
class ProviderSignUpInfoSignUpInfot : Fragment() {

    private  lateinit var binding: FragmentSignUpInfoBinding
    private lateinit var auth: FirebaseAuth
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

        var currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
        //creates new user type
        db.collection("userType")
            .whereEqualTo("Email", currentUserEmail)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var temp = document.get("type").toString()
                    var docId = document.id
                    if(temp.equals("user")){
                        // Add a new document with a generated ID
                        db.collection("Providers")
                            .add(newUser)
                            .addOnSuccessListener {
                                val docRef = db.collection("userType").document(docId)
                                db.runTransaction { transaction ->
                                    transaction.update(docRef, "type", "master")
                                    transaction.update(docRef, "current", "provider")
                                }.addOnSuccessListener { result ->
                                    val intent = Intent (getActivity(), ProvidersMainActivity::class.java)
                                    getActivity()?.startActivity(intent)
                                }.addOnFailureListener { e ->
                                    binding.progressBar.setVisibility(View.GONE)
                                    binding.errorTxt.setVisibility(View.VISIBLE)
                                }
                            }
                            .addOnFailureListener {
                                binding.progressBar.setVisibility(View.GONE)
                                binding.errorTxt.setVisibility(View.VISIBLE)
                            }
                    }

                }
            }.addOnFailureListener {
                //creates new user type
                val newUserType = hashMapOf(
                    "Email" to currentUserEmail,
                    "current" to "provider",
                    "type" to "provider"
                )

                db.collection("Providers")
                    .add(newUser)
                    .addOnSuccessListener {
                        db.collection("userType")
                            .add(newUserType)
                            .addOnSuccessListener {
                                val intent = Intent (getActivity(), ProvidersMainActivity::class.java)
                                getActivity()?.startActivity(intent)
                            }
                            .addOnFailureListener {
                                binding.progressBar.setVisibility(View.GONE)
                                binding.errorTxt.setVisibility(View.VISIBLE)
                            }
                    }
                    .addOnFailureListener {
                        binding.progressBar.setVisibility(View.GONE)
                        binding.errorTxt.setVisibility(View.VISIBLE)
                    }
            }



    }


}