package com.u3coding.cactus.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Created by u3 on 17-8-17.
 */
class Api{
    var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    var retrofit = Retrofit.Builder()
            .baseUrl("http://api.u3coding.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun getService(): ApiInterface {
        var service = retrofit.create(ApiInterface::class.java)
        return service
    }
}