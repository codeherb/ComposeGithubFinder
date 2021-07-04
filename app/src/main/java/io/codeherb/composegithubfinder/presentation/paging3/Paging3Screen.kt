package io.codeherb.composegithubfinder.presentation.paging3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import io.codeherb.composegithubfinder.R
import io.codeherb.composegithubfinder.data.model.GithubUser
import io.codeherb.composegithubfinder.presentation.SampleScreen
import io.codeherb.composegithubfinder.presentation.Screen
import io.codeherb.composegithubfinder.presentation.controller.Paging3ScreenViewModel
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun InfinityListWithPaging3Screen(viewModel: Paging3ScreenViewModel = hiltViewModel(), navController: NavHostController = rememberNavController()) {
    val items = viewModel.pagingItems.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()
    println("@## items state - ${items.loadState} - ${items.loadState.source}")
    Screen(title = SampleScreen.InfinityListWithPaging3.name, navController = navController) {
        Column {
            InputArea(
                modifier = Modifier.fillMaxWidth(),
                onInputMessage = { keyword ->
                    coroutineScope.launch {
                        viewModel.searchUser(keyword)
                    }
                }
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
            LazyColumn(modifier = Modifier.weight(1f)) {
                when {
                    items.itemCount > 0 -> {
                        itemsIndexed(lazyPagingItems = items) { idx, value ->
                            GithubUserCard(value) {
                                println("@## getRefreshKey request = $idx")
                                items.refresh()
                            }
                        }
                    }
                    else -> {
                        item {
                            Text(
                                text = "아이템 없음",
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GithubUserCard(data: GithubUser?, onClick: () -> Unit = {}) {
    val painter = rememberCoilPainter(
        request = data?.avatarUrl ?: "https://picsum.photos/300/300",
        previewPlaceholder = R.drawable.ic_launcher_background,
    )
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Box(modifier = Modifier.size(100.dp)) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
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
            Text(text = data?.name ?: "", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(data?.description ?: "", style = MaterialTheme.typography.body2)
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