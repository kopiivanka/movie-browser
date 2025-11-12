package ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Movie
import ui.components.NetworkImage

@Composable
fun MovieListScreen(
    state: MovieListState,
    onToggleFavorite: (Int) -> Unit,
    onToggleFilter: () -> Unit,
    onSelect: (Movie) -> Unit
) {
    Column {
        Row(
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(if (state.showFavoritesOnly) "Favorites" else "All Movies",
                style = MaterialTheme.typography.h5)
            Button(onClick = onToggleFilter) {
                Text(if (state.showFavoritesOnly) "Show All" else "Show Favorites")
            }
        }

        if (state.loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return
        }

        val list = if (state.showFavoritesOnly)
            state.movies.filter { it.id in state.favorites }
        else state.movies

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(list) { m ->
                Row(
                    Modifier.fillMaxWidth()
                        .clickable { onSelect(m) }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NetworkImage(m.posterUrl, Modifier.size(100.dp))
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(m.title, style = MaterialTheme.typography.h6)
                        Text(m.overview.take(100) + "…")
                    }
                    IconButton(onClick = { onToggleFavorite(m.id) }) {
                        val fav = m.id in state.favorites
                        Icon(if (fav) Icons.Filled.Star else Icons.Outlined.StarBorder, null)
                    }
                }
                Divider()
            }
        }
    }
}