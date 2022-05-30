package com.example.trainingmoonshotapp.app.ui

import FossilList
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun FossilsNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable(FossilScreens.FossilsList.name) {
            FossilList(navController = navController)
        }

        composable(FossilScreens.FossilDetails.name + "/{name}",
            arguments = listOf(
            navArgument("name") { type = NavType.StringType}
        )) {
            val name = it.arguments?.getString("name")?: ""
            FossilDetails(name, navController = navController)
        }
    }
}