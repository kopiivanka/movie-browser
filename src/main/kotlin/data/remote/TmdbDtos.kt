package data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbMovieResponse(val results: List<TmdbMovieDto>)

@Serializable
data class TmdbMovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String = ""
)

internal const val TMDB_IMG = "https://image.tmdb.org/t/p/w500"
