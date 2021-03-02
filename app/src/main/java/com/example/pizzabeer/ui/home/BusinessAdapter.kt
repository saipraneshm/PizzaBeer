package com.example.pizzabeer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzabeer.R
import com.example.pizzabeer.domain.model.Business

class BusinessAdapter : RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {

    private val businessList: ArrayList<Business> = arrayListOf()

    fun addAllBusinesses(businesses: List<Business>) {
        if (businessList.isNotEmpty()) businessList.clear()
        businessList.addAll(businesses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_businesses, parent, false)
        return BusinessViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        holder.bindBusiness(businessList[position])
    }

    override fun getItemCount(): Int {
        return businessList.size
    }

    inner class BusinessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val businessNameTv = itemView.findViewById<TextView>(R.id.businessNameTv)
        private val businessAddressTv = itemView.findViewById<TextView>(R.id.businessAddress)
        private val businessImage = itemView.findViewById<ImageView>(R.id.foodIv)

        fun bindBusiness(business: Business) {
            businessNameTv.text = business.name
            businessAddressTv.text = business.location.fullAddress

            Glide.with(itemView)
                .load(business.image_url)
                .centerCrop()
                .into(businessImage)
        }
    }
}