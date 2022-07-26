package com.tolgahan.android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tolgahan.android.R
import com.tolgahan.android.core.fragment.BaseFragment
import com.tolgahan.android.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel by viewModels<DetailViewModel>()

    private val args: DetailFragmentArgs by navArgs()

    private var isClicked: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.getMovie(args.id)
        binding.favIV.setOnClickListener {
            viewModel.favClicked(!isClicked)
        }

        viewModel.isClicked.observe(viewLifecycleOwner) {
            this.isClicked = it
            binding.isClicked = it
        }
    }
}