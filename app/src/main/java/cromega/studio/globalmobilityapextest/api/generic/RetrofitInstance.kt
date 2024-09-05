package cromega.studio.globalmobilityapextest.api.generic

import cromega.studio.globalmobilityapextest.api.generic.RetrofitInstance.retrofit
import cromega.studio.globalmobilityapextest.api.interceptors.ApiKeyInterceptor
import cromega.studio.globalmobilityapextest.api.interceptors.DataLimitInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object to instance and contain the Retrofit object for API interfaces instantiation.
 *
 * @property retrofit [Retrofit] instance build with the [GiphyApiConstants] values
 */
object RetrofitInstance
{
    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(GiphyApiConstants.GIPHY_BASE_URL)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(ApiKeyInterceptor())
                    .addInterceptor(DataLimitInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}