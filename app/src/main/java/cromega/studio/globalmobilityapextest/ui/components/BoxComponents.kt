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
