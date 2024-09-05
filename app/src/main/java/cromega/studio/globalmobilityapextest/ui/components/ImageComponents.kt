package cromega.studio.globalmobilityapextest.ui.components

import android.content.Context
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    data: String,
    contentDescription: String? = null
) = Image(
        painter =
            rememberAsyncImagePainter(
                model =
                    ImageRequest
                        .Builder(context)
                        .data(data)
                        .size(Size.ORIGINAL)
                        .build(),
                imageLoader =
                    ImageLoader
                        .Builder(context)
                        .components {
                            add(
                                if (Build.VERSION.SDK_INT >= 28)
                                    ImageDecoderDecoder.Factory()
                                else GifDecoder.Factory()
                            )
                        }
                        .build()
            ),
        contentDescription = contentDescription,
        modifier = modifier
    )
