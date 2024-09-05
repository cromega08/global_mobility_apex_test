package cromega.studio.globalmobilityapextest.api.interfaces

import cromega.studio.globalmobilityapextest.models.GifFull
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyApiInterface {
    @GET("trending")
    fun getTrending(): Call<GifFull>

    @GET("search")
    fun searchGifs(@Query("q") query: String): Call<GifFull>
}