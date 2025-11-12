package data.remote

import data.remote.ApiClient.http
import domain.model.Movie
import domain.repo.MovieRepository
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbMovieRepository : MovieRepository {

    private val apiKey: String by lazy {
        val env = dotenv { ignoreIfMalformed = true; ignoreIfMissing = true }
        env["TMDB_API_KEY"] ?: error("TMDB_API_KEY missing in .env")
    }

    override suspend fun popular(page: Int): List<Movie> {
        val resp: TmdbMovieResponse = http.get("https://api.themoviedb.org/3/movie/popular") {
            parameter("api_key", apiKey)
            parameter("language", "en-US")
            parameter("page", page)
        }.body()

        return resp.results.map { dto ->
            Movie(
                id = dto.id,
                title = dto.title,
                overview = dto.overview,
                posterUrl = dto.posterPath?.let { p ->
                    if (p.startsWith("http")) p else "$TMDB_IMG$p"
                },
                year = dto.releaseDate.take(4)
            )
        }
    }
}
