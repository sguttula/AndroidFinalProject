package com.example.myfinalproject


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myfinalproject.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth


class LogInFragment : Fragment() {

    private  lateinit var binding: FragmentLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        var userState = FirebaseAuth.getInstance().currentUser?.email?.split("-")

        //if user has logged in then it will be redirect to the main activity
        if(auth.currentUser != null){
            val userType = userState?.get(0)
            if(userType.equals("user")){
                val intent = Intent (getActivity(), MainActivity::class.java)
                getActivity()?.startActivity(intent)
            }else if(userType.equals("provider") ){
                val intent = Intent (getActivity(), ProvidersMainActivity::class.java)
                getActivity()?.startActivity(intent)
            }

        }

        //Otherwise it will start the login fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false )
        //hides progress bar
        binding.progressBar.setVisibility(View.GONE)
        //login btn listener
        binding.login.setOnClickListener {
            binding.progressBar.setVisibility(View.VISIBLE)
            if (binding.switch1.isChecked){
                val pref = "user"
                var stateEmail = pref.plus("-").plus(binding.email.text)
                logInUser(stateEmail, binding.password, binding.authText, binding.progressBar)

            }else{
                val pref = "provider"
                var stateEmail = pref.plus("-").plus(binding.email.text)
                logInProvider(stateEmail, binding.password, binding.authText, binding.progressBar)
            }
        }
        //Signup btn listener
        binding.signupbtn.setOnClickListener {view: View ->
            if (binding.switch1.isChecked){
                view.findNavController().navigate(R.id.action_logInFragment_to_userSignUpFragment)
            }else{
                view.findNavController().navigate(R.id.action_logInFragment_to_providerSignUpFragment)
            }
        }

        binding.forgotPass.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_logInFragment_to_forgotPasswordFragment)
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.switch1.text = "switch to Provider"
                binding.mainText.setText(R.string.user)
//                mainImg.setImageResource(R.drawable.user)
            } else {
                binding.switch1.text = "Switch to User"
                binding.mainText.setText(R.string.provider)
//                mainImg.setImageResource(R.drawable.provider)
            }
        }

        return binding.root
    }

    //TODO: check values email and pass before sending the request
    //Function that will handle the log in
    private fun logInUser(user: String, pass: EditText, test: TextView, rotatingBar: ProgressBar){

        auth.signInWithEmailAndPassword(user, pass.text.toString())
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    rotatingBar.setVisibility(View.GONE)
                    val intent = Intent (getActivity(), MainActivity::class.java)
                    getActivity()?.startActivity(intent)
                }else{
                    rotatingBar.setVisibility(View.GONE)
                    test.text = "Log in failed"
                }
            }


    }

    //TODO: check values email and pass before sending the request
    //Function that will handle the log in
    private fun logInProvider(user: String, pass: EditText, test: TextView, rotatingBar: ProgressBar){

        auth.signInWithEmailAndPassword(user, pass.text.toString())
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    rotatingBar.setVisibility(View.GONE)
                    val intent = Intent (getActivity(), ProvidersMainActivity::class.java)
                    getActivity()?.startActivity(intent)
                }else{
                    rotatingBar.setVisibility(View.GONE)
                    test.text = "Log in failed"
                }
            }


    }


}
