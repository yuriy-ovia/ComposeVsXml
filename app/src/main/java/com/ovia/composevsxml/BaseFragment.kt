package com.ovia.composevsxml

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ovia.composevsxml.network.ApiServiceFactory
import com.ovia.composevsxml.network.NetworkingRepository
import com.ovia.composevsxml.network.OkHttpFactory
import kotlinx.coroutines.launch

abstract class BaseFragment: Fragment() {
    lateinit var viewModel: SearchViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val okHttpClient = OkHttpFactory.getClient(context?.applicationContext)
        // Create Retrofit instance used to make API calls
        val apiService = ApiServiceFactory.create(okHttpClient, "https://www.omdbapi.com/")
        viewModel = ViewModelProvider(this, SearchViewModel.SearchViewModelFactory(NetworkingRepository(apiService)))[SearchViewModel::class.java]

        lifecycleScope.launch {
            viewModel.performQuery("green")
        }
    }
}
