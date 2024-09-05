package cromega.studio.globalmobilityapextest.ui.activities.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cromega.studio.globalmobilityapextest.models.GifInfo
import cromega.studio.globalmobilityapextest.models.Image
import cromega.studio.globalmobilityapextest.ui.components.Gif
import cromega.studio.globalmobilityapextest.ui.components.SearchBar
import cromega.studio.globalmobilityapextest.ui.components.WarningIcon


@Composable
fun Screen(mainViewModel: MainViewModel)
{
    val gifs: List<GifInfo> by mainViewModel.gifs.observeAsState(emptyList())
    val responseError: Boolean = mainViewModel.responseError
    val networkError: Boolean = mainViewModel.networkError
    val error: Boolean = responseError || networkError

    Scaffold(
        topBar = { Header(mainViewModel = mainViewModel) },
        content = {
            if (error) {
                val message: String =
                    if (networkError) "No Internet Connection"
                    else "Request Error"

                ErrorBody(paddingValues = it, errorMessage = message)
            }
            else Body(paddingValues = it, gifs = gifs)
        }
    )
}
@Composable
fun Header(mainViewModel: MainViewModel) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Color.White)
    ) {
        SearchBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 35.dp,
                        bottom = 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
            hint = "Search",
            query = query,
            onQueryChange = {
                query = it

                if (query.isNullOrBlank()) mainViewModel.getTrending()
                            },
            onSearch = {
                val toSearch: String = query

                if (toSearch.isNullOrBlank()) mainViewModel.getTrending()
                else mainViewModel.searchGifs(toSearch)
            }
        )
    }
}

@Composable
fun Body(paddingValues: PaddingValues, gifs: List<GifInfo>) {
    val existGifs: Boolean = gifs.isNotEmpty()
    val amount: Int = if (existGifs) gifs.size - 1 else 20

    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 10.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = paddingValues
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

@Composable
fun ErrorBody(paddingValues: PaddingValues, errorMessage: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WarningIcon()
        Text(errorMessage)
    }
}
