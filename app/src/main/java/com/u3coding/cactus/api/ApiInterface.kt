package com.u3coding.cactus.api

import retrofit2.Call
import retrofit2.http.*


/**
 * Created by u3 on 17-8-17.
 */
interface  ApiInterface{
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("name")name:String, @Field("psw")psw:String): Call<LoginBean>
    @FormUrlEncoded
    @POST("getgames")
    fun getGames(@Field("id") id:String): Call<GetGameBean>
    @FormUrlEncoded
    @POST("calrate")
    fun calGame(@Field("id") id:String,@Field("rates") rate:String): Call<CalGameBean>
    @FormUrlEncoded
    @POST("signup")
    fun signup(@Field("name")name: String,@Field("psw")psw:String,@Field("email")email:String): Call<SignupBean>
}
