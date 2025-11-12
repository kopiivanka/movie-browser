package ui.nav

import domain.model.Movie

sealed class Screen {
    data object List : Screen()
    data class Details(val movie: Movie) : Screen()
}
