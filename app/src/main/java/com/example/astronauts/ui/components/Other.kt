package com.example.astronauts.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * Create a [Spacer] of given width in [dp]
 */
@Composable
fun HorizontalSpacer(size: Int) = Spacer(modifier = Modifier.width(size.dp))

/**
 * Create a [Spacer] of given height in [dp]
 */
@Composable
fun VerticalSpacer(size: Int) = Spacer(modifier = Modifier.height(size.dp))

/**
 * Create a center aligned [CircularProgressIndicator] wrapped in a [Box]
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 4.dp
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size)
                .align(Alignment.Center),
            color = color,
            strokeWidth = strokeWidth
        )
    }
}

@Composable
fun ErrorCard(modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Something went wrong")
        Button(onClick = { onClick() }) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun CustomImageView(url: String, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = ColorPainter(Color.Cyan),
        error = ColorPainter(Color.Red),
        fallback = ColorPainter(Color.Cyan),
        contentDescription = "Astronaut profile picture",
        modifier = modifier
    )
}
