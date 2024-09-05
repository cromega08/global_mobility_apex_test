package cromega.studio.globalmobilityapextest.ui.activities.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cromega.studio.globalmobilityapextest.models.GifInfo
import cromega.studio.globalmobilityapextest.models.Image
import cromega.studio.globalmobilityapextest.models.PaginationInfo
import cromega.studio.globalmobilityapextest.ui.components.Gif


@Composable
fun Screen(mainViewModel: MainViewModel = MainViewModel())
{
    val gifs: List<GifInfo> = mainViewModel.gifs
    val existGifs: Boolean = gifs.isNotEmpty()
    val paginationInfo: PaginationInfo? = mainViewModel.paginationInfo
    val amount: Int = paginationInfo?.count ?: 20

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(
            top = 25.dp,
            bottom = 50.dp,
            start = 10.dp,
            end = 10.dp
        )
    ) {
        items(amount) {index ->
            val data: String
            val aspectRatio: Float

            if (existGifs)
            {
                val gif: Image = gifs[index].images.downsized
                data = gif.url
                aspectRatio = gif.width.toFloat() / gif.height.toFloat()
            }
            else
            {
                data = ""
                aspectRatio = (16 / 9).toFloat()
            }

            Gif(
                modifier =
                Modifier
                    .aspectRatio(aspectRatio)
                    .fillMaxWidth(),
                data = data
            )
        }
    }
}
