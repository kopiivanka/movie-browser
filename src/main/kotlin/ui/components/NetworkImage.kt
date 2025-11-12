package ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import core.ImageCache
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*
import org.jetbrains.skia.Image

private val client by lazy { HttpClient(CIO) }
private val cache = ImageCache(80)

@Composable
fun NetworkImage(url: String?, modifier: Modifier = Modifier) {
    if (url == null) return

    var bitmap by remember(url) { mutableStateOf(cache.get(url)) }

    LaunchedEffect(url) {
        if (bitmap == null) {
            val bytes = client.get(url).bodyAsChannel().toByteArray()
            val img = Image.makeFromEncoded(bytes).toComposeImageBitmap()
            cache.put(url, img)
            bitmap = img
        }
    }

    bitmap?.let { Image(it, contentDescription = null, modifier = modifier) }
}
