package com.u3coding.cactus

import android.app.Application
import com.facebook.stetho.Stetho
import okhttp3.OkHttpClient

/**
 * Created by u3 on 17-8-19.
 */
class MyApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}