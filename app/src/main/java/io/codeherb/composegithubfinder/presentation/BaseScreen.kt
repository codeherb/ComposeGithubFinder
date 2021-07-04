package io.codeherb.composegithubfinder.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun Screen(title: String, modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Image(Icons.Outlined.ArrowBack, contentDescription = "뒤로")
                        }
                    }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier
    ) {
        content()
    }
}

enum class SampleScreen {
    Root,
    NavigationWithArgument,
    InputTextWithState,
    InfinityList,
    InfinityListWithPaging3
}