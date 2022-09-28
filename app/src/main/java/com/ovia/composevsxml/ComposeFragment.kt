package com.ovia.composevsxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.ovia.composevsxml.databinding.FragmentComposeBinding
import com.ovia.composevsxml.network.DataState

class ComposeFragment : BaseFragment() {

    private var _binding: FragmentComposeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentComposeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    MoviePager()
                }
            }
        }
        return view
    }

    @Composable
    fun MoviePager() {
        when (val uiState = viewModel.state.collectAsState().value) {
            DataState.Loading -> DisplaySimpleText(text = "loading")
            DataState.Error -> DisplaySimpleText(text = "error")
            is DataState.Success -> {
                when (uiState.type) {
                    DataState.SuccessType.NOT_EMPTY -> DisplaySimpleText(text = "success")
                    DataState.SuccessType.SEARCH_START -> DisplaySimpleText(text = "search start")
                    DataState.SuccessType.SEARCH_NO_RESULTS -> DisplaySimpleText(text = "no results")
                }
            }
        }
    }

    @Composable
    fun DisplaySimpleText(text: String) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = text)
        }
    }
}
