package io.codeherb.composegithubfinder.presentation.main

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.codeherb.composegithubfinder.presentation.SampleScreen
import io.codeherb.composegithubfinder.presentation.Screen

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Screen(title = "Compose와 함께 춤을!") {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (button0, button1, button2, button3) = createRefs()
            createVerticalChain(button0, button1, button2, button3, chainStyle = ChainStyle.Packed)

            Button(
                onClick = {
                    navController.navigate("${SampleScreen.NavigationWithArgument.name}/테스트")
                },
                modifier = Modifier
                    .constrainAs(button0) {
                        top.linkTo(parent.top)
                        bottom.linkTo(button1.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .defaultMinSize(minWidth = 300.dp)
                    .padding(vertical = 15.dp)
            ) {
                Text(text = "NavigationWithArgument")
            }

            Button(
                onClick = {
                    navController.navigate(SampleScreen.InputTextWithState.name)
                },
                modifier = Modifier
                    .constrainAs(button1) {
                        top.linkTo(button0.bottom)
                        bottom.linkTo(button2.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .defaultMinSize(minWidth = 300.dp)
                    .padding(vertical = 15.dp)
            ) {
                Text(text = "Input Text With State")
            }

            Button(
                onClick = {
                    navController.navigate(SampleScreen.InfinityList.name)
                },
                modifier = Modifier
                    .constrainAs(button2) {
                        top.linkTo(button1.bottom)
                        bottom.linkTo(button3.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .defaultMinSize(minWidth = 300.dp)
                    .padding(vertical = 15.dp)
            ) {
                Text(text = "Infinity List")
            }

            Button(
                onClick = {
                    navController.navigate(SampleScreen.InfinityListWithPaging3.name)
                },
                modifier = Modifier
                    .constrainAs(button3) {
                        top.linkTo(button2.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .defaultMinSize(minWidth = 300.dp)
                    .padding(vertical = 15.dp)
            ) {
                Text(text = "Infinity List With Paging3")
            }
        }
    }
}