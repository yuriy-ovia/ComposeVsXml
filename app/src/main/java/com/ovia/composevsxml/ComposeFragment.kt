package com.ovia.composevsxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment

class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ScreenContent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContent() {
    Column(modifier = Modifier.background(Color.White)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_ovia_logo),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.hello_second_fragment),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.second_fragment_subtitle),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 48.dp).fillMaxWidth(.65f)
            ) {
                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.first_cta))
                }
                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.second_cta))
                }
            }
        }
    }
}
