package com.u3coding.cactus.rategame

import android.app.Activity
import android.graphics.Bitmap
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.u3coding.cactus.R

/**
 * Created by u3-linux on 8/22/17.
 */
class MyWebClient(var activity:Activity):WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

        return true
    }

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        view?.scrollTo(0,1100)
        var pb = activity.findViewById<ProgressBar>(R.id.pro_pb)
        pb.visibility = View.INVISIBLE
        var fl = activity.findViewById<FrameLayout>(R.id.main_fl)
        fl.visibility = View.VISIBLE
        var tv = activity.findViewById<TextView>(R.id.loading_tv)
        tv.visibility = View.INVISIBLE
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        var pb = activity.findViewById<ProgressBar>(R.id.pro_pb)
        pb.visibility = View.VISIBLE
        var fl = activity.findViewById<FrameLayout>(R.id.main_fl)
        fl.visibility = View.INVISIBLE
        var tv = activity.findViewById<TextView>(R.id.loading_tv)
        tv.visibility = View.VISIBLE
    }
}