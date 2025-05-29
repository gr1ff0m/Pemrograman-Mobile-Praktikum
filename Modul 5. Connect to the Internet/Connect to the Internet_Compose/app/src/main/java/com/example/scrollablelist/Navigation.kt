package com.example.scrollablelist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelist.screens.DetailScreen
import com.example.scrollablelist.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("detail/{title}/{imageUrl}/{fullDescription}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
            val fullDescription = backStackEntry.arguments?.getString("fullDescription") ?: ""

            DetailScreen(
                title = title,
                imageUrl = imageUrl,
                fullDescription = fullDescription,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
