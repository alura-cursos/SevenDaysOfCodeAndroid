package br.com.alura.devhub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.alura.devhub.AppScreens.*
import br.com.alura.devhub.ui.screen.AuthenticationScreen
import br.com.alura.devhub.ui.screen.ProfileScreen
import br.com.alura.devhub.ui.theme.DevHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevHubTheme {
                var screenState: AppScreens by remember {
                    mutableStateOf(Authentication)
                }
                var user by remember {
                    mutableStateOf("")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when (screenState) {
                        Authentication -> {
                            AuthenticationScreen(
                                onEnterClick = {
                                    if (it.isNotBlank()) {
                                        user = it
                                        screenState = Profile
                                    }
                                }
                            )
                        }
                        Profile -> {
                            ProfileScreen(user)
                        }
                    }

                }
            }
        }
    }
}

sealed class AppScreens {
    object Authentication : AppScreens()
    object Profile : AppScreens()
}