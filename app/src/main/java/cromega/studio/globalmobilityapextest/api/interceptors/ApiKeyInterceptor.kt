package cromega.studio.globalmobilityapextest.api.interceptors

import cromega.studio.globalmobilityapextest.api.generic.GiphyApiConstants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * [Interceptor] for Giphy API requests, used to insert the "[GiphyApiConstants.GIPHY_API_KEY]" value
 * as query parameter in the URL automatically.
 */
class ApiKeyInterceptor : Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response
    {
        val originalRequest = chain.request()

        val newUrl =
            originalRequest
                .url
                .newBuilder()
                .addQueryParameter("api_key", GiphyApiConstants.GIPHY_API_KEY)
                .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}