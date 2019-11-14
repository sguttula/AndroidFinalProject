package com.example.myfinalproject


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_providers.view.*
import java.io.File
import javax.xml.transform.Templates

/**
 * A simple [Fragment] subclass.
 */
class ProvidersFragment : Fragment() {


    companion object {
        private const val TAG = "KotlinQueryActivity"
    }


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
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        mProvider = ArrayList()
        providerAdapter = context?.let { ProviderAdapter(it, mProvider as ArrayList<Provider>, true) }
        recyclerView.adapter = providerAdapter

        view.search_edit_text.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               if (view.search_edit_text.text.toString() == ""){

               }
                else{
                   recyclerView.visibility = View.VISIBLE

                   retrieveUsers()
                   searchProvider(p0.toString().toLowerCase())
               }
            }

            override fun afterTextChanged(p0: Editable?) {
            }


        })

        return view
    }

    private fun searchProvider(input: String) {

        val query = FirebaseDatabase.getInstance().getReference()
            .child("Providers")
            .orderByChild("washType")
            .startAt(input)
            .endAt(input + "\uf8ff")

        query.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                mProvider.clear()

                for (snapshot in dataSnapshot.children){
                    val provider = snapshot.getValue(Provider::class.java)
                    if (provider != null){
                        mProvider.add(provider)
                    }
                }
                providerAdapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }

        })

    }

    private fun retrieveUsers() {
        val providerRef = FirebaseDatabase.getInstance().reference.child("email")

        providerRef.addValueEventListener(object: ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (view?.search_edit_text?.text.toString()==""){
                    mProvider.clear()

                    for (snapshot in dataSnapshot.children){
                        val provider = snapshot.getValue(Provider::class.java)
                        if (provider != null){
                            mProvider.add(provider)
                        }
                    }
                    providerAdapter?.notifyDataSetChanged()
                }
            }

        })
    }


}
