package com.u3coding.cactus.api

/**
 * Created by u3 on 17-8-17.
 */
data class LoginBean(var code:Int,var userid:String)
data class SignupBean(var code:Int)
data class GetGameBean(var id:ArrayList<Int>,var sid:ArrayList<Int>)
data class CalGameBean(var sid:ArrayList<Int>)
