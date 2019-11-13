package com.example.myfinalproject


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myfinalproject.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private  lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false )
        binding.progressBar.setVisibility(View.GONE)

        binding.login.setOnClickListener {
            if (binding.switch1.isChecked){
                val intent = Intent (getActivity(), MainActivity::class.java)
                getActivity()?.startActivity(intent)
            }else{
                val intent = Intent (getActivity(), ProvidersMainActivity::class.java)
                getActivity()?.startActivity(intent)
            }
        }

        binding.signupbtn.setOnClickListener {view: View ->
            if (binding.switch1.isChecked){
                view.findNavController().navigate(R.id.action_logInFragment_to_singUpFragment)
            }else{
                view.findNavController().navigate(R.id.action_logInFragment_to_singUpFragment)
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


}
