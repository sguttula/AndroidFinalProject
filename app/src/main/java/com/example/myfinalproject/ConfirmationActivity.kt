package com.example.myfinalproject

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myfinalproject.databinding.ConfirmationBinding
import kotlinx.android.synthetic.main.confirmation.*

class ConfirmationActivity: Fragment() {

    private lateinit var binding: ConfirmationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.confirmation, container, false )

        confirmPage()

        return binding.root
    }

    private fun confirmPage() {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var characterNum = ""
        for (i in 0..10) {
            characterNum += chars[Math.floor(Math.random() * chars.length).toInt()]
        }
        textViewConfirmationNumber.setText(characterNum)

        buttonGoBackHome.setOnClickListener {
            activity?.let{
                val intent = Intent (it, MapFragment::class.java)
                it.startActivity(intent)
            }
        }
    }
}