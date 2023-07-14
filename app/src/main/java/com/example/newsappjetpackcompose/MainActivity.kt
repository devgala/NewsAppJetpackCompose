package com.example.newsappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme
import com.example.newsappjetpackcompose.viewmodel.LoginViewModel
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel
import com.example.newsappjetpackcompose.webViewNav.WebViewNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val newsViewModel: NewsViewModel by viewModels()
    private val searchViewModel: SearchScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
//            setKeepVisibleCondition{
//               // newsViewModel.isLoading.value
//            }
            setKeepOnScreenCondition{
                loginViewModel.doneInit.value!!
            }
        }
        setContent {
            NewsAppJetpackComposeTheme {
//                MainScreen(newsViewModel = newsViewModel, searchViewModel = searchViewModel)
                WebViewNav(
                    newsViewModel = newsViewModel,
                    searchViewModel = searchViewModel
                )
            }
        }

    }
}










@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppJetpackComposeTheme {
     // MainScreen()
    }
}