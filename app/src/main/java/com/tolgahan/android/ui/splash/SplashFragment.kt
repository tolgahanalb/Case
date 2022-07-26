package com.tolgahan.android.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tolgahan.android.R
import com.tolgahan.android.core.fragment.BaseFragment
import com.tolgahan.android.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash){

    val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initNavigation()
    }

    private fun initNavigation() {
            findNavController().navigate(SplashFragmentDirections.navigateToList())
    }
}