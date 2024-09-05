package cromega.studio.globalmobilityapextest.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/**
 * [Composable] component to contain the [GifImage] and a background loader while the GIF is rendered.
 *
 * @param modifier [Modifier] to modify the container and the GIF
 * @param context [Context] instances to render the GIF
 * @param data [String] representing the URI of the desired GIF to render
 * @param contentDescription [String]? to describe the GIF image, improving the accessibility
 */
@Composable
fun Gif(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    data: String,
    contentDescription: String? = null
) =
    Box(
        modifier =
            modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.zIndex(1f)
        )
        GifImage(
            modifier = modifier.zIndex(2f),
            context = context,
            data = data,
            contentDescription = contentDescription
        )
    }
