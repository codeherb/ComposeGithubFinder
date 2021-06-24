package io.codeherb.composegithubfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import io.codeherb.composegithubfinder.presentation.SampleScreen
import io.codeherb.composegithubfinder.presentation.Screen
import io.codeherb.composegithubfinder.presentation.infinitylist.InfinityListScreen
import io.codeherb.composegithubfinder.presentation.inputtext.InputTextWithStateScreen
import io.codeherb.composegithubfinder.presentation.main.MainScreen
import io.codeherb.composegithubfinder.presentation.paging3.InfinityListWithPaging3Screen
import io.codeherb.composegithubfinder.ui.theme.ComposeGithubFinderTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = SampleScreen.Root.name) {
                    composable(SampleScreen.Root.name) {
                        MainScreen(navController)
                    }
                    composable(SampleScreen.InputTextWithState.name) {
                        InputTextWithStateScreen()
                    }
                    composable(SampleScreen.InfinityList.name) {
                        InfinityListScreen(navController = navController)
                    }
                    composable(SampleScreen.InfinityListWithPaging3.name) {
                        InfinityListWithPaging3Screen(navController = navController)
                    }
                    composable("${SampleScreen.NavigationWithArgument.name}/{name}", listOf(navArgument("name") { type = NavType.StringType })) {
                        Screen(
                            title = SampleScreen.NavigationWithArgument.name,
                            navController = navController
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text("Argument = ${it.arguments?.getString("name") ?: "None"}", modifier = Modifier.align(Alignment.Center))
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeGithubFinderTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}