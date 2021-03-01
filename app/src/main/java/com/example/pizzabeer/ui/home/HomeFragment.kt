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
                (binding.businessesRv.adapter as BusinessAdapter).addAllBusinesses(it)
            }
        }
    }

    private fun setupUI() {
        // initialize any adapters or set initial value.
        binding.termsRv.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = TermAdapter().apply {
                addAllTerms(listOf("pizza", "beer"))
            }
        }

        binding.businessesRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = BusinessAdapter()
        }
    }
}