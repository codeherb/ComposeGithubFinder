package io.codeherb.composegithubfinder.presentation.infinitylist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import io.codeherb.composegithubfinder.R
import io.codeherb.composegithubfinder.presentation.controller.MainViewModel
import io.codeherb.composegithubfinder.presentation.SampleScreen
import io.codeherb.composegithubfinder.presentation.Screen

@Composable
fun InfinityListScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavHostController = rememberNavController()) {
    val items by viewModel.items.collectAsState(emptyList<String>())
    val lastIndex = items.size - 1
    Screen(title = SampleScreen.InfinityList.name, navController= navController) {
        LazyColumn {
            itemsIndexed(items) { i, item ->
                if (lastIndex == i) {
                    LaunchedEffect(key1 = "ListScreen") {
                        viewModel.loadMore()
                    }
                }
                SimpleCard(name = item)
            }
        }
    }
}

@Composable
fun SimpleCard(name: String, onClick: () -> Unit = {}) {
    val painter = rememberCoilPainter(
        request = "https://picsum.photos/300/300",
        previewPlaceholder = R.drawable.ic_launcher_background,
    )
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Box(modifier = Modifier.size(100.dp)) {
            Image(
                painter = painter,
                contentDescription = null
            )
            if (painter.loadState is ImageLoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = name, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }

}