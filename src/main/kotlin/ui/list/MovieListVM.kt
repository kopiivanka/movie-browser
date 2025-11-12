package ui.list

import data.local.FavoritesStore
import data.remote.TmdbMovieRepository
import domain.model.Movie
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val favorites: Set<Int> = emptySet(),
    val loading: Boolean = false,
    val error: String? = null,
    val showFavoritesOnly: Boolean = false
)

class MovieListVM(
    private val repo: TmdbMovieRepository = TmdbMovieRepository(),
    private val fav: FavoritesStore = FavoritesStore(),
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) {
    private val _state = MutableStateFlow(MovieListState(favorites = fav.get()))
    val state = _state.asStateFlow()

    fun load() {
        _state.update { it.copy(loading = true, error = null) }
        scope.launch {
            runCatching { repo.popular() }
                .onSuccess { list ->
                    _state.update { it.copy(movies = list, loading = false) }
                }
                .onFailure { e ->
                    _state.update { it.copy(loading = false, error = e.message ?: "Unknown error") }
                }
        }
    }

    fun toggleFavorite(id: Int) {
        _state.update { it.copy(favorites = fav.toggle(id)) }
    }

    fun toggleFilter() {
        _state.update { it.copy(showFavoritesOnly = !it.showFavoritesOnly) }
    }
}
