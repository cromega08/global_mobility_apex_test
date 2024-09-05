package cromega.studio.globalmobilityapextest.ui.activities.main

import android.util.Log
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
import cromega.studio.globalmobilityapextest.models.PaginationInfo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel()
{
    private val api: GiphyApiInterface = RetrofitInstance.retrofit.create(GiphyApiInterface::class.java)

    private val _gifs: MutableLiveData<List<GifInfo>> = MutableLiveData<List<GifInfo>>()
    private val _paginationInfo: MutableState<PaginationInfo?> = mutableStateOf(null)

    val gifs: LiveData<List<GifInfo>>
        get() = _gifs

    val paginationInfo: PaginationInfo?
        get() = _paginationInfo.value

    init {
        getTrending()
    }

    fun getTrending()
    {
        viewModelScope.launch {
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
                            _paginationInfo.value = gifFull.pagination
                        }

                        override fun onFailure(call: Call<GifFull>, t: Throwable)
                        {
                            Log.e("", "")
                        }
                    }
                )
        }
    }

    fun searchGifs(query: String) {
        viewModelScope.launch {
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
                            _paginationInfo.value = gifFull.pagination
                        }

                        override fun onFailure(call: Call<GifFull>, t: Throwable)
                        {
                            Log.e("", "")
                        }
                    }
                )
        }
    }
}