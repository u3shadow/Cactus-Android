package com.u3coding.cactus

import okhttp3.RequestBody
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
    fun getGames(@Field("id") id:String): Call<List<GetGameBean>>
    @FormUrlEncoded
    @POST("signup")
    fun signup(@Field("name")name: String,@Field("psw")psw:String,@Field("email")email:String): Call<SignupBean>
}
