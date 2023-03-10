package com.example.astronauts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.astronauts.ui.astronautlist.AstronautListScreen
import com.example.astronauts.ui.astronautlist.AstronautListViewModel
import com.example.astronauts.ui.detail.AstronautDetailScreen
import com.example.astronauts.ui.detail.AstronautDetailViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            val astronautListViewModel: AstronautListViewModel = hiltViewModel()

            AstronautListScreen(
                navigateToDetails = { id -> navController.navigate("details/$id") },
                astronautListViewModel = astronautListViewModel
            )
        }
        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val astronautDetailViewModel: AstronautDetailViewModel = hiltViewModel()
            AstronautDetailScreen(
                navigateUp = { navController.navigateUp() },
                astronautDetailViewModel = astronautDetailViewModel,
            )
        }
    }
}
