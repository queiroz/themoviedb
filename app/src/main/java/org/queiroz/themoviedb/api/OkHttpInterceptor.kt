package org.queiroz.themoviedb.api

import okhttp3.Interceptor
import okhttp3.Response
import org.queiroz.themoviedb.BuildConfig
import javax.inject.Singleton

@Singleton
class OkHttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url
        val url = requestUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }

}
