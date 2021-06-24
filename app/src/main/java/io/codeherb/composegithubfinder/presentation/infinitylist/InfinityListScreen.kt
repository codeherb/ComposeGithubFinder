package io.codeherb.composegithubfinder.presentation.infinitylist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.codeherb.composegithubfinder.presentation.controller.MainViewModel
import io.codeherb.composegithubfinder.presentation.GithubUserCard
import io.codeherb.composegithubfinder.presentation.SampleScreen
import io.codeherb.composegithubfinder.presentation.Screen

@Composable
fun InfinityListScreen(viewModel: MainViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
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
                GithubUserCard(name = item)
            }
        }
    }
}