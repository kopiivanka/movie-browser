package core

import androidx.compose.ui.graphics.ImageBitmap

class ImageCache(private val maxEntries: Int = 64) {
    private val map = object :
        LinkedHashMap<String, ImageBitmap>(16, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, ImageBitmap>?) =
            size > maxEntries
    }

    fun get(key: String) = map[key]
    fun put(key: String, img: ImageBitmap) {
        map[key] = img
    }
}
