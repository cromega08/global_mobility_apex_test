package cromega.studio.globalmobilityapextest.api.interfaces

import cromega.studio.globalmobilityapextest.models.GifFull
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface used to define the endpoints of the Giphy API requests, expecting to be instantiated
 * for the "[RetrofitInstance.retrofit]" object.
 */
interface GiphyApiInterface {

    /**
     * Endpoint to retrieve the GIFs in "trending" in Giphy.
     *
     * @return [Call]<[GifFull]> response automatically armed by [Retrofit]
     */
    @GET("trending")
    fun getTrending(): Call<GifFull>

    /**
     * Endpoint to retrieve the Gifs that can match the "query" in Giphy.
     *
     * @param query [String] that represent the keywords to filter the GIFs
     *
     * @return [Call]<[GifFull]> response automatically armed by [Retrofit]
     */
    @GET("search")
    fun searchGifs(@Query("q") query: String): Call<GifFull>
}