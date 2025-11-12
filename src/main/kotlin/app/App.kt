package app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import domain.model.Movie
import ui.details.MovieDetailsScreen
import ui.details.MovieDetailsVM
import ui.list.MovieListScreen
import ui.list.MovieListVM
import ui.nav.Screen

@Composable
fun App() {
    MaterialTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.List) }
        val listVM = remember { MovieListVM() }
        val detailsVM = remember { MovieDetailsVM() }

        val st by listVM.state.collectAsState()

        LaunchedEffect(Unit) { listVM.load() }

        when (val s = screen) {
            is Screen.List -> {
                MovieListScreen(
                    state = st,
                    onToggleFavorite = { listVM.toggleFavorite(it) },
                    onToggleFilter = { listVM.toggleFilter() },
                    onSelect = { m -> screen = Screen.Details(m) })
            }

            is Screen.Details -> {
                val m: Movie = s.movie
                var fav by remember { mutableStateOf(detailsVM.isFavorite(m.id)) }
                MovieDetailsScreen(
                    movie = m,
                    isFavorite = fav,
                    onToggleFavorite = { fav = m.id !in detailsVM.toggle(m.id) },
                    onBack = { screen = Screen.List })
            }
        }
    }
}
