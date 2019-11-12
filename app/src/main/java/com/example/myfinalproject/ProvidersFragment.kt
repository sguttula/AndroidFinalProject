package com.example.myfinalproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinalproject.Adapter.ProviderAdapter
import com.example.myfinalproject.Model.Provider
import com.example.myfinalproject.databinding.FragmentMapBinding
import com.example.myfinalproject.databinding.FragmentProvidersBinding

/**
 * A simple [Fragment] subclass.
 */
class ProvidersFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private  var providerAdapter: ProviderAdapter? = null
    private lateinit var mProvider: MutableList<Provider>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val binding: FragmentProvidersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_providers,container, false)
        val view = inflater.inflate( R.layout.fragment_providers,container, false)

        recyclerView = view.findViewById(R.id.recycler_view_search)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        mProvider = ArrayList()
        providerAdapter = context?.let { ProviderAdapter(it, mProvider as ArrayList<Provider>, true) }
        recyclerView.adapter = providerAdapter

        return view
    }


}
