package com.ovia.composevsxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ovia.composevsxml.databinding.FragmentComposeBinding
import com.ovia.composevsxml.models.Result
import com.ovia.composevsxml.network.DataState
import java.util.Locale

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
                    MoviePage()
                }
            }
        }
        return view
    }

    @Composable
    fun MoviePage() {
        when (val uiState = viewModel.state.collectAsState().value) {
            DataState.Loading -> SimpleText(text = "loading")
            DataState.Error -> SimpleText(text = "error")
            is DataState.Success -> {
                when (uiState.type) {
                    DataState.SuccessType.NOT_EMPTY -> MoviePagePager()
                    DataState.SuccessType.SEARCH_START -> SimpleText(text = "search start")
                    DataState.SuccessType.SEARCH_NO_RESULTS -> SimpleText(text = "no results")
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MoviePagePager() {
        HorizontalPager(count = 10) { index ->
            val result = viewModel.data.value?.searchResults?.get(index) ?: return@HorizontalPager
            MovieCard(result = result)
        }
    }

    @Composable
    fun MovieCard(result: Result) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            elevation = 10.dp,
            backgroundColor = colorResource(id = R.color.light_green)
        ) {
            val title = result.title ?: "missing title"
            val year = result.year ?: "missing year"
            val type = result.type?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            } ?: "missing type"
            val posterUrl =
                result.poster ?: "https://www.sarras-shop.com/out/pictures/master/product/1/no-image-available-icon.jpg"
            Row(modifier = Modifier.padding(8.dp)) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(.4f)
                )
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        Text(
                            text = year,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "•",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                        )
                        Text(
                            text = type,
                            fontSize = 16.sp
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.lorem_ipsum),
                        fontSize = 14.sp,
                        maxLines = 9,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun SimpleText(text: String) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text)
        }
    }
}
