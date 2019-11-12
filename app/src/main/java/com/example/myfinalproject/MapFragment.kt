package com.example.myfinalproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.myfinalproject.R.*
import com.example.myfinalproject.R.id.action_mapFragment_to_providersFragment
import com.example.myfinalproject.R.layout.*
import com.example.myfinalproject.databinding.FragmentMapBinding


class MapFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        binding.findProvidersButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_mapFragment_to_providersFragment)
        )
        return binding.root
    }

}
