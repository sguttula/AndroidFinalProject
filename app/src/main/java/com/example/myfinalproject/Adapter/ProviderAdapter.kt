package com.example.myfinalproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinalproject.Model.Provider
import com.example.myfinalproject.R

class ProviderAdapter (private var mContext: Context,
                       private var mProvider: List<Provider>,
                       private var isFragment: Boolean = false): RecyclerView.Adapter<ProviderAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderAdapter.ViewHolder {
            val view = LayoutInflater.from(mContext).inflate(R.layout.provider_item_layout, parent, false)
           return ProviderAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return  mProvider.size

    }

    override fun onBindViewHolder(holder: ProviderAdapter.ViewHolder, position: Int) {
        val provider = mProvider[position]
        holder.userNameProvider.text = provider.getProvidername()
        holder.fullNameProvider.text = provider.getProviderfullname()
        holder.emailProvider.text = provider.getProvideremail()
        holder.washType.text = provider.getwashType()
    }

    class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView){
        var userNameProvider: TextView = itemView.findViewById(R.id.provider_username)
        var fullNameProvider:TextView  = itemView.findViewById(R.id.provider_fullname)
        var emailProvider: TextView  = itemView.findViewById(R.id.provider_email)
        var washType: TextView  = itemView.findViewById(R.id.wash_type)
        var bookButton: Button = itemView.findViewById(R.id.bookwash_button)
    }


}