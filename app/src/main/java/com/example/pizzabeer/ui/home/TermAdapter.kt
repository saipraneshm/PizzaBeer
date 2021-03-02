package com.example.pizzabeer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabeer.R
import com.example.pizzabeer.ui.models.Terms
import com.google.android.material.button.MaterialButton

class TermAdapter(private val itemClickListener: OnItemClickListener? = null) :
    RecyclerView.Adapter<TermAdapter.TermViewHolder>() {

    private val termsList: ArrayList<Terms> = arrayListOf()

    interface OnItemClickListener {
        fun onTermSelected(term: Terms)
    }

    fun addAllTerms(terms: List<Terms>) {
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

    inner class TermViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val termsBtn = itemView.findViewById<MaterialButton>(R.id.termsBtn)

        init {
            termsBtn.setOnClickListener(this)
        }

        fun bindTerms(term: Terms) {
            termsBtn.text = term.displayValue
        }

        override fun onClick(v: View?) {
            itemClickListener?.onTermSelected(termsList[adapterPosition])
        }
    }
}
