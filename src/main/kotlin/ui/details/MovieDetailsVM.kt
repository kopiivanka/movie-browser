package ui.details

import data.local.FavoritesStore

class MovieDetailsVM(private val fav: FavoritesStore = FavoritesStore()) {
    fun isFavorite(id: Int) = fav.get().contains(id)
    fun toggle(id: Int) = fav.toggle(id)
}
