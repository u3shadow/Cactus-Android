package com.u3coding.cactus.base

import android.app.Fragment
import android.view.View
import android.widget.Toast

/**
 * Created by u3-linux on 8/23/17.
 */
abstract class BaseFragment: Fragment(){
    abstract fun initView(view: View)
    abstract fun initLis()
    fun showToast(errors:String){
        Toast.makeText(activity,errors,Toast.LENGTH_LONG).show()
    }
}