package cromega.studio.globalmobilityapextest.ui.activities.main

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cromega.studio.globalmobilityapextest.api.generic.RetrofitInstance
import cromega.studio.globalmobilityapextest.api.interfaces.GiphyApiInterface
import cromega.studio.globalmobilityapextest.models.GifFull
import cromega.studio.globalmobilityapextest.models.GifInfo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ViewModel class for the "Main*" activity of the app.
 * Contains the GiphyApiInterface instance for api calls
 * and handle the most import States and data related to requests.
 *
 * @param connectivityManager ConectivityManager instance required
 * for ensuring the Internet connection of the device
 */
class MainViewModel(
    private val connectivityManager: ConnectivityManager
) : ViewModel()
{
    private val api: GiphyApiInterface = RetrofitInstance.retrofit.create(GiphyApiInterface::class.java)

    private val _gifs: MutableLiveData<List<GifInfo>> = MutableLiveData<List<GifInfo>>()
    private val _responseError: MutableState<Boolean> = mutableStateOf(false)
    private val _networkError: MutableState<Boolean> = mutableStateOf(false)

    val gifs: LiveData<List<GifInfo>>
        get() = _gifs

    val responseError: Boolean
        get() = _responseError.value

    val networkError: Boolean
        get() = _networkError.value

    var lastSearch: String = ""

    init {
        getTrending()
    }

    /**
     * Request designed to retrieve the "default" data through "trending" Giphy endpoint.
     * Result are saved through MainViewModel States update.
     */
    fun getTrending()
    {
        viewModelScope.launch {
            lastSearch = ""

            if (!hasNetwork())
            {
                _networkError.value = true
            }
            else {
                _networkError.value = false
                _gifs.value = emptyList()

                api
                    .getTrending()
                    .enqueue(
                        object : Callback<GifFull>
                        {
                            override fun onResponse(call: Call<GifFull>, response: Response<GifFull>)
                            {
                                if (!response.isSuccessful) {
                                    return
                                }

                                val gifFull: GifFull = response.body()!!

                                _gifs.value = gifFull.data
                                _responseError.value = false
                            }

                            override fun onFailure(call: Call<GifFull>, t: Throwable)
                            {
                                _responseError.value = true
                            }
                        }
                    )
            }
        }
    }

    /**
     * Request designed to retrieve filtered results through "search" Giphy endpoint.
     * Result are saved through MainViewModel States update.
     *
     * @param query String that represent the query to be send, establishing the Gifs to be received
     */
    fun searchGifs(query: String)
    {
        viewModelScope.launch {
            lastSearch = query

            if (!hasNetwork())
            {
                _networkError.value = true
            }
            else {
                _networkError.value = false
                _gifs.value = emptyList()

                api
                    .searchGifs(query)
                    .enqueue(
                        object : Callback<GifFull>
                        {
                            override fun onResponse(call: Call<GifFull>, response: Response<GifFull>)
                            {
                                if (!response.isSuccessful) {return}

                                val gifFull: GifFull = response.body()!!

                                _gifs.value = gifFull.data
                                _responseError.value = false
                            }

                            override fun onFailure(call: Call<GifFull>, t: Throwable)
                            {
                                _responseError.value = true
                            }
                        }
                    )
            }
        }
    }


    /**
     * Private function, used to check if the device has connection before calling the Giphy API requests.
     * Current function depends on MainViewModel.connectivityManager property.
     *
     * @return Boolean value indicating if the current device has Internet connection
     */
    private fun hasNetwork(): Boolean
    {
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager.activeNetwork?.let {
                connectivityManager.getNetworkCapabilities(it)
            }

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}