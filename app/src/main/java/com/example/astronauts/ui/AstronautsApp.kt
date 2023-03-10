package com.example.astronauts.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.astronauts.ui.navigation.AppNavigation
import com.example.astronauts.ui.theme.AstronautsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautsApp() {
    AstronautsTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}
