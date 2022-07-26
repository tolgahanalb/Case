package com.tolgahan.android.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tolgahan.android.R
import com.tolgahan.android.core.extension.hideKeyboard
import com.tolgahan.android.core.fragment.BaseFragment
import com.tolgahan.android.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {

    private var lastQuery: String = ""
    private val viewModel by viewModels<ListViewModel>()

    private lateinit var adapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initAdapter()

        var job: Job? = null
        viewModel.searchText.observe(viewLifecycleOwner) {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.isNotEmpty() && (it == lastQuery).not()) {
                        lastQuery = it
                        viewModel.getList(it)
                    }
                }
            }
        }
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideKeyboard()
        }
    }

    private fun initAdapter() {
        adapter = ListAdapter {
            findNavController().navigate(ListFragmentDirections.navigateToDetail(it.id.toString()))
        }
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recycler.adapter = adapter
    }
}