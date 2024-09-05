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

    fun searchGifs(query: String) {
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

    private fun hasNetwork(): Boolean
    {
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager.activeNetwork?.let {
                connectivityManager.getNetworkCapabilities(it)
            }

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}