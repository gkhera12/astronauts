package com.example.astronauts.ui.astronautlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.ui.components.*
import com.example.astronauts.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautListScreen(
    navigateToDetails: (Int) -> Unit,
    astronautListViewModel: AstronautListViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Astronauts") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ScreenContent(
                modifier = Modifier.fillMaxSize(),
                astronautListViewModel,
                navigateToDetails
            )
        }
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    astronautListViewModel: AstronautListViewModel,
    navigateToDetails: (Int) -> Unit
) {
    val uiState = astronautListViewModel.uiState.observeAsState()
    when (uiState.value) {
        is AstronautsUiState.Loading -> LoadingIndicator(modifier = modifier)
        is AstronautsUiState.Error -> ErrorCard(modifier) { astronautListViewModel.initialise() }
        is AstronautsUiState.Success -> AstronautsList(
            modifier,
            (uiState.value as AstronautsUiState.Success).astronauts,
            { astronautListViewModel.onSortClicked() },
            navigateToDetails
        )
        else -> {}
    }

}

@Composable
fun AstronautsList(
    modifier: Modifier,
    astronauts: List<Astronaut>,
    onSortClicked: () -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    OutlinedButton(modifier = Modifier.padding(horizontal = 8.dp),
        onClick = { onSortClicked() }) {
        Text(text = "Click to Sort")
    }

    VerticalSpacer(size = 8)

    LazyColumn(
        modifier = modifier,
    ) {
        items(astronauts.size) { index ->
            val astronaut = astronauts[index]
            AstronautCard(astronaut, navigateToDetails)
        }
    }
}

@Composable
private fun AstronautCard(astronaut: Astronaut, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable(onClick = { onClick(astronaut.id) })
    ) {
        Row {
            CustomImageView(url = astronaut.profileImage, modifier = Modifier.size(60.dp))

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = astronaut.name)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = astronaut.nationality)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AstronautCardPreview() {
    AstronautCard(
        Astronaut(
            id = 1,
            name = "Anthony",
            nationality = "Aussie",
            profileImage = "sample.com"
        ), { 1 }
    )
}
