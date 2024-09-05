package cromega.studio.globalmobilityapextest.ui.activities.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cromega.studio.globalmobilityapextest.models.GifInfo
import cromega.studio.globalmobilityapextest.models.Image
import cromega.studio.globalmobilityapextest.ui.components.Gif
import cromega.studio.globalmobilityapextest.ui.components.SearchBar
import cromega.studio.globalmobilityapextest.ui.components.WarningIcon

/**
 * [Composable] main method to render the UI of the app through a [Scaffold].
 *
 * @param mainViewModel [MainViewModel] instance to retrieve data and
 * be passed through different son components
 */
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

                ErrorBody(paddingValues = it, errorMessage = message, mainViewModel = mainViewModel)
            }
            else Body(paddingValues = it, gifs = gifs)
        }
    )
}

/**
 * [Composable] component to render the top part of the UI ([Scaffold].topBar)
 *
 * @param mainViewModel [MainViewModel] instance to call requests
 */
@Composable
private fun Header(mainViewModel: MainViewModel) {
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

/**
 * [Composable] component to render the content retrieved for the important data
 *
 * @param paddingValues [PaddingValues] to implement border space for the rendered content
 * @param gifs [List]<[GifInfo]> instance, representing the data to be rendered
 */
@Composable
private fun Body(paddingValues: PaddingValues, gifs: List<GifInfo>) {
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

/**
 * [Composable] component designed to be a "fallback" for "[Body]" [Composable],
 * in case there's an error with the Network or Request's
 *
 * @param paddingValues [PaddingValues] to implement border space for the rendered content
 * @param errorMessage [String] to be displayed as informative message on the "Error Components"
 * @param mainViewModel [MainViewModel] instance to call requests when "retrying the connection"
 */
@Composable
private fun ErrorBody(paddingValues: PaddingValues, errorMessage: String, mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context: Context = LocalContext.current

        WarningIcon()

        Text(errorMessage)

       Spacer(
           modifier = Modifier.fillMaxWidth().height(10.dp)
       )

        Button(onClick = {
            if (mainViewModel.lastSearch.isBlank()) mainViewModel.getTrending()
            else mainViewModel.searchGifs(mainViewModel.lastSearch)

            Toast.makeText(context, "Retrying...", Toast.LENGTH_SHORT).show()
        }) {
            Text("Retry")
        }
    }
}
