package com.u3coding.cactus.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient




/**
 * Created by u3 on 17-8-17.
 */
class Api{
    var okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    var retrofit = Retrofit.Builder()
            .baseUrl("http://45.76.194.215/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun getService(): ApiInterface {
        var service = retrofit.create(ApiInterface::class.java)
        return service
    }
}