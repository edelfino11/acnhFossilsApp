package com.example.trainingmoonshotapp.app.ui

import Navbar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.trainingmoonshotapp.domain.Fossil
import com.example.trainingmoonshotapp.ui.theme.*

@Composable
fun FossilDetails(
    name: String,
    navController: NavHostController,
    viewModel: DetailFossilViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    viewModel.getFossilDetails(name)

    trainingmoonshotappTheme {
        Box(modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
        ) {
            when (state) {
                FossilState.Loading ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            color = DarkerButtonBlue,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                is FossilState.ShowFossil -> {
                    Column {
                        Navbar(navController)
                        DetailedCard(fossil = state.fossil)
                    }
                }
            }
        }
    }
}


@Composable
fun DetailedCard(fossil: Fossil){
    val name = fossil.name
    val group = fossil.fossil_group
    val sell = fossil.name
    val width = fossil.width
    val length = fossil.length

    Column() {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(all = 10.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .padding(all = 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(LighterBlue)){
                Image(
                    painter = rememberAsyncImagePainter(model = fossil.image_url),
                    contentDescription = name,
                    modifier = Modifier
                        .size(200.dp)
                )
            }
            Column {
                Text(text = "Fossil Group: $group", style = MaterialTheme.typography.h3)
                Text(text = "Sold For: $sell", style = MaterialTheme.typography.body1)
                Text(
                    text = "Width x Length: $width x $length",
                    style = MaterialTheme.typography.body1
                )
                Text(text = "Colors: ", style = MaterialTheme.typography.body1)
                fossil.colors.map {
                    Text(text = it, style = MaterialTheme.typography.body1)
                }
            }
        }
    }

}
