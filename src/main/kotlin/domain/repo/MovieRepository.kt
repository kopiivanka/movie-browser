package domain.repo

import domain.model.Movie

interface MovieRepository {
    suspend fun popular(page: Int = 1): List<Movie>
}
