package cromega.studio.globalmobilityapextest.api.interceptors

import cromega.studio.globalmobilityapextest.api.generic.GiphyApiConstants
import okhttp3.Interceptor
import okhttp3.Response

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