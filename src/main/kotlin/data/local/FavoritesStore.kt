package data.local

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class FavoritesStore(private val file: File = File("favorites.json")) {
    fun get(): MutableSet<Int> =
        if (file.exists()) Json.decodeFromString(file.readText()) else mutableSetOf()

    fun toggle(id: Int): MutableSet<Int> {
        val set = get()
        if (!set.add(id)) set.remove(id)
        file.writeText(Json.encodeToString(set))
        return set
    }
}