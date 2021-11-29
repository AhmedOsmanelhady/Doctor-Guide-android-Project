package com.example.lastApp.ui.doctors

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.lastApp.R
import com.example.lastApp.adapters.DoctorAdapter
import com.example.lastApp.databinding.DoctorFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorFragment : Fragment(R.layout.doctor_fragment) {

    private var _binding: DoctorFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DoctorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DoctorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recyclerview with header and footer
        val doctorAdapter = DoctorAdapter()
        binding.apply {
            rvDoctor.setHasFixedSize(true)
            rvDoctor.adapter = doctorAdapter.withLoadStateHeaderAndFooter(
                header = DoctorLoadStateAdapter { doctorAdapter.retry() },
                footer = DoctorLoadStateAdapter { doctorAdapter.retry() },
            )
            btnRetryRv.setOnClickListener {
                doctorAdapter.retry()
            }
        }

        setHasOptionsMenu(true)

        doctorAdapter.addLoadStateListener { LoadState ->
            binding.apply {
                doctorProgressBarRv.isVisible = LoadState.source.refresh is LoadState.Loading
                rvDoctor.isVisible = LoadState.source.refresh is LoadState.NotLoading
                btnRetryRv.isVisible = LoadState.source.refresh is LoadState.Error
                tvDataRv.isVisible = LoadState.source.refresh is LoadState.Error
                //empty view
                if (LoadState.source.refresh is LoadState.NotLoading
                    && LoadState.append.endOfPaginationReached
                    && doctorAdapter.itemCount < 1
                ) {
                    rvDoctor.isVisible = false
                    tvEmptyRv.isVisible = true
                } else {
                    tvEmptyRv.isVisible = false
                }
            }
        }

        viewModel.doctors.observe(viewLifecycleOwner) {
            doctorAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    // searchView
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.doctor_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvDoctor.scrollToPosition(0)
                    viewModel.getDoctors(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    binding.rvDoctor.scrollToPosition(0)
                    viewModel.getDoctors(newText)
                    searchView.clearFocus()
                }
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
















