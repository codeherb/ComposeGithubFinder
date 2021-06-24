package io.codeherb.composegithubfinder.presentation.paging3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import io.codeherb.composegithubfinder.presentation.controller.MainViewModel
import io.codeherb.composegithubfinder.presentation.GithubUserCard
import io.codeherb.composegithubfinder.presentation.SampleScreen
import io.codeherb.composegithubfinder.presentation.Screen

@ExperimentalComposeUiApi
@Composable
fun InfinityListWithPaging3Screen(viewModel: MainViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    val items = viewModel.pagingItems.collectAsLazyPagingItems()
    Screen(title = SampleScreen.InfinityListWithPaging3.name, navController = navController) {
        Column {
            InputArea(
                modifier = Modifier.fillMaxWidth(),
                onInputMessage = viewModel::searchUser
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
            LazyColumn {
                itemsIndexed(lazyPagingItems = items) { idx, value ->
                    GithubUserCard(name = value ?: "") {
                        println("@## getRefreshKey request = $idx")
                        items.refresh()
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun InputArea(modifier: Modifier, onInputMessage: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    fun sendInputMessage() {
        onInputMessage(inputText)
        inputText = ""
    }
    Row(modifier = modifier) {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f, fill = true)
                .padding(vertical = 8.dp)
                .padding(start = 10.dp)
                .onKeyEvent { keyEvent ->
                    if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Enter) {
                        sendInputMessage()
                        return@onKeyEvent true
                    }
                    return@onKeyEvent false
                }
        )
        Button(
            onClick = ::sendInputMessage,
            modifier = Modifier.align(Alignment.CenterVertically),
            enabled = inputText.isNotBlank(),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent,
            ),
        ) {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun preview() {
    InputArea(
        Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
    ) {

    }
}