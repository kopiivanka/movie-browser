package ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Movie
import ui.components.NetworkImage

@Composable
fun MovieDetailsScreen(
    movie: Movie,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
            Text("Details", style = MaterialTheme.typography.h6)
        }
        Spacer(Modifier.height(8.dp))
        NetworkImage(movie.posterUrl, Modifier.size(250.dp))
        Spacer(Modifier.height(12.dp))
        Text(movie.title, style = MaterialTheme.typography.h5)
        Text("Year: ${movie.year}")
        Spacer(Modifier.height(8.dp))
        Text(movie.overview)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onToggleFavorite) {
            Icon(if (isFavorite) Icons.Default.Star else Icons.Outlined.StarBorder, null)
            Spacer(Modifier.width(8.dp))
            Text(if (isFavorite) "Remove from favorites" else "Add to favorites")
        }
    }
}
