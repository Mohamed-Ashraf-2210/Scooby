package com.example.data.remote.apis

import com.example.data.Constant
import com.example.data.local.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val token = TokenManager.getAuth(this, Constant.USER_TOKEN)

    val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(mHttpLoggingInterceptor)
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer " + sharedPreferencesManager.getToken())
                .addHeader("Accept", "application/json")
                .addHeader("Api-Version", "v1")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    private var mRetrofit: Retrofit? = null


    val client: Retrofit?
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit
        }
}