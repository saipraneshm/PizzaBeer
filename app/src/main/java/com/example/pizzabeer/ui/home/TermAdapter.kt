package com.example.pizzabeer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabeer.R

class TermAdapter : RecyclerView.Adapter<TermAdapter.TermViewHolder>() {

    private val termsList: ArrayList<String> = arrayListOf()

    fun addAllTerms(terms: List<String>) {
        if (termsList.isNotEmpty()) termsList.clear()
        termsList.addAll(terms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_term, parent, false)
        return TermViewHolder(view)
    }

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        holder.bindTerms(termsList[position])
    }

    override fun getItemCount(): Int {
        return termsList.size
    }

    inner class TermViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val termsText = itemView.findViewById<TextView>(R.id.termsTv)

        fun bindTerms(name: String) {
            termsText.text = name
        }
    }
}
