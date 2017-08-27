package com.u3coding.cactus.game

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
class MyGameWebClient(var activity:Activity):WebViewClient(){
    private var url:String? = null
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if(url?.contains("agecheck")!!||url?.contains(this.url!!)!!) {
            view!!.loadUrl(url)
            return true
        }
        else
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

    override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
        view!!.reload()
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        this.url = url
        var pb = activity.findViewById<ProgressBar>(R.id.pro_pb)
        pb.visibility = View.VISIBLE
        var fl = activity.findViewById<FrameLayout>(R.id.main_fl)
        fl.visibility = View.INVISIBLE
        var tv = activity.findViewById<TextView>(R.id.loading_tv)
        tv.visibility = View.VISIBLE
    }
}