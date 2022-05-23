package br.com.alura.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.devhub.ui.theme.DevHubTheme
import br.com.alura.devhub.webclient.GitHubWebClient
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileScreen("alexfelipe")
                }
            }
        }
    }

}

@Composable
fun ProfileScreen(
    user: String,
    webClient: GitHubWebClient = GitHubWebClient()
) {
    val foundUser by webClient.findProfileBy(user)
        .collectAsState(initial = null)
    foundUser?.let { userProfile ->
        Column {
            val boxHeight = remember {
                150.dp
            }
            val imageHeight = remember {
                boxHeight
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF2d333b), shape = RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .height(boxHeight)
            ) {
                AsyncImage(
                    userProfile.avatar,
                    contentDescription = "userProfile pic",
                    placeholder = painterResource(R.drawable.user_placeholder),
                    modifier = Modifier
                        .offset(y = imageHeight / 2)
                        .size(imageHeight)
                        .align(Alignment.BottomCenter)
                        .clip(CircleShape),
                )
            }
            Spacer(modifier = Modifier.height(imageHeight / 2))
            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    userProfile.name,
                    fontSize = 32.sp
                )
                Text(
                    userProfile.login,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                userProfile.bio,
                Modifier
                    .padding(
                        start = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(user = "alexfelipe")
}