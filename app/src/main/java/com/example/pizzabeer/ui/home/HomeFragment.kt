package com.example.pizzabeer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabeer.R
import com.example.pizzabeer.databinding.HomeFragmentBinding
import com.example.pizzabeer.domain.model.Business
import com.example.pizzabeer.ui.models.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        // view model observers
        viewModel.run {
            locationLiveData.observe(viewLifecycleOwner) {
                binding.locationTv.text =
                    getString(R.string.showing_businesses_near, it.fullAddress)
            }

            businessesLiveData.observe(viewLifecycleOwner) {
                handleResult(it)
            }
        }
    }

    private fun handleResult(result: NetworkResult<List<Business>>) {
        when (result) {
            Loading -> {
                // show loading progress
            }
            is NetworkError -> {
                // hide loading progress
                // currently just showing any errors with a snackbar
                context?.let {
                    Snackbar.make(
                        it,
                        binding.root,
                        result.exception.localizedMessage ?: "unable to load data",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            is OK -> {
                // hide loading progress
                (binding.businessesRv.adapter as BusinessAdapter).addAllBusinesses(result.data)
            }
        }
    }

    private fun setupUI() {
        // initialize any adapters or set initial value.
        binding.termsRv.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            val clickListener = object : TermAdapter.OnItemClickListener {
                override fun onTermSelected(term: Terms) {
                    // using the current location and the selected term to fetch the location again.
                    viewModel.locationLiveData.value?.let {
                        viewModel.searchBusinesses(term.displayValue, it)
                    }
                }
            }
            adapter = TermAdapter(clickListener).apply {
                addAllTerms(Terms.values().toList())
            }
        }

        binding.businessesRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = BusinessAdapter()
        }
    }
}