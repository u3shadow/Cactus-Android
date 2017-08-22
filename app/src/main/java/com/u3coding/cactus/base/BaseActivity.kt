package com.u3coding.cactus.base

import android.app.Activity
import android.view.View

/**
 * Created by u3 on 17-8-16.
 */
abstract class BaseActivity:Activity(){
    abstract fun initView()
    abstract fun initLis()
}