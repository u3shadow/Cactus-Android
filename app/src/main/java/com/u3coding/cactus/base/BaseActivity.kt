package com.u3coding.cactus.base

import android.app.Activity
import android.view.View
import android.widget.Toast

/**
 * Created by u3 on 17-8-16.
 */
abstract class BaseActivity:Activity(){
    abstract fun initView()
    abstract fun initLis()
    fun showToast(errors:String){
        Toast.makeText(this,errors,Toast.LENGTH_LONG).show()
    }
}
