package com.ovia.composevsxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ovia.composevsxml.ui.theme.DoubleSpacing
import com.ovia.composevsxml.ui.theme.LoginApplicationTheme
import com.ovia.composevsxml.ui.theme.LoginImagePadding
import com.ovia.composevsxml.ui.theme.PrimaryButtonHeight
import com.ovia.composevsxml.ui.theme.QuintupleHalfSpacing
import com.ovia.composevsxml.ui.theme.TextMediumToLarge
import com.ovia.composevsxml.ui.theme.TextUltraLarge
import com.ovia.composevsxml.ui.theme.TripleHalfSpacing

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
                LoginApplicationTheme {
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun LoginPage() {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LoginImagePadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_music_2),
                contentDescription = stringResource(R.string.login_screen_image)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .padding(DoubleSpacing)
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.sign_in),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    ),
                    fontSize = TextUltraLarge
                )
                Spacer(modifier = Modifier.padding(QuintupleHalfSpacing))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = { emailValue.value = it },
                        label = { Text(stringResource(R.string.email_address)) },
                        placeholder = { Text(stringResource(R.string.email_address)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = { passwordValue.value = it },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = null,
                                )
                            }
                        },
                        label = { Text(stringResource(R.string.password)) },
                        placeholder = { Text(stringResource(R.string.password)) },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester)
                    )

                    Spacer(modifier = Modifier.padding(TripleHalfSpacing))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(PrimaryButtonHeight)
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            fontSize = TextMediumToLarge
                        )
                    }

                    Spacer(modifier = Modifier.padding(QuintupleHalfSpacing))
                    Text(text = stringResource(R.string.create_account))
                    Spacer(modifier = Modifier.padding(QuintupleHalfSpacing))
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}
