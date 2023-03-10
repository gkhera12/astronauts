package com.example.astronauts.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.astronauts.R
import com.example.astronauts.datalayer.models.Astronaut
import com.example.astronauts.ui.components.CustomImageView
import com.example.astronauts.ui.components.ErrorCard
import com.example.astronauts.ui.components.LoadingIndicator
import com.example.astronauts.ui.components.VerticalSpacer
import com.example.astronauts.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AstronautDetailScreen(
    navigateUp: () -> Unit,
    astronautDetailViewModel: AstronautDetailViewModel,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Astronauts Details") },
                navigationIcon = {
                    IconButton(onClick = { navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            DetailScreenContent(modifier = Modifier.fillMaxSize(), astronautDetailViewModel)
        }
    }
}

@Composable
private fun DetailScreenContent(modifier: Modifier, astronautDetailViewModel: AstronautDetailViewModel) {
    val uiState = astronautDetailViewModel.uiState.observeAsState()
    when (uiState.value) {
        AstronautDetailUiState.Error -> ErrorCard(modifier = modifier) { astronautDetailViewModel.initialise() }
        AstronautDetailUiState.Loading -> LoadingIndicator(modifier = modifier)
        is AstronautDetailUiState.Success -> {
            val astronaut = (uiState.value as AstronautDetailUiState.Success).astronaut
            AstronautDetailCard(modifier, astronaut)
        }
        else -> {}
    }
}

@Composable
fun AstronautDetailCard(modifier: Modifier, astronaut: Astronaut) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(1) {
            CustomImageView(
                url = astronaut.profileImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(10.dp)
            )

            Title(astronaut.name)
            DetailItem(stringResource(R.string.nationality), astronaut.nationality)
            astronaut.dateOfBirth?.let { DetailItem(stringResource(R.string.dob), it) }
            astronaut.bio?.let { DetailItem(stringResource(R.string.bio), it) }
        }
    }
}

@Composable
private fun Title(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
private fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        VerticalSpacer(size = 8)
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
        VerticalSpacer(size = 8)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Visible
        )
    }
}

@Preview(device = Devices.NEXUS_5X)
@Composable
fun AccoutCardPreview() {
    val astronaut = Astronaut(
        id = 1,
        name = "OK",
        nationality = "USA",
        "any.com",
        "This is a bio long after lorem ipsumgThis is a biThis is a bio long after lorem ipsumgThis is a biThis is a bio long after lorem ipsumgThis is a biThis is a bio long after lorem ipsumgThis is a bio long after lorem ipsumgThis is a bio long after lorem ipsumg had a cat andy any would have done anytingThis is a bio long after lorem ipsumg had a cat andy any would have done anytingThis is a bio long after lorem ipsumg had a cat andy any woadfasdfsadf  asdfasdf uld have done anyting",
        "22/03/2012"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ) {
        AstronautDetailCard(Modifier.fillMaxSize(), astronaut = astronaut)
    }
}

