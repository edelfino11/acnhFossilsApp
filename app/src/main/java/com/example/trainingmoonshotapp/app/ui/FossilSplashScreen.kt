package com.example.trainingmoonshotapp.app.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.trainingmoonshotapp.ui.theme.DarkerButtonBlue
import com.example.trainingmoonshotapp.ui.theme.DeepBlue
import com.example.trainingmoonshotapp.ui.theme.LighterBlue
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ) {
        // AnimationEffect
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            delay(3000L)
            navController.navigate(FossilScreens.FossilsList.name)
        }

        // Image
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column() {
                Image(
                    painter = rememberAsyncImagePainter(
                        "" +
                                "https://dodo.ac/np/images/f/fc/Identified_Fossil_NH_Inv_Icon.png"
                    ),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .scale(scale.value)
                        .size(200.dp)
                )
                Text(
                    text = "Animal Crossing Fossil App",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1,
                    color = DarkerButtonBlue
                )
            }
        }
    }
}