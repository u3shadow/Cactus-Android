package com.u3coding.cactus
import android.os.Bundle
import android.webkit.WebView
import android.widget.ViewFlipper
import android.webkit.WebViewClient



class WebViewActivity:BaseActivity(){
    private var flipper:ViewFlipper?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)
        initView()
        initLis()
    }
    override fun initView() {
        flipper = findViewById(R.id.flipper)
    }

    override fun initLis() {
        flipper!!.addView(addWebView("http://store.steampowered.com/app/535520"));
        flipper!!.addView(addWebView("http://store.steampowered.com/app/678520"));
    }

    private fun addWebView(url: String): MyWebView {

      var  myWebView:MyWebView = MyWebView(this,flipper)
        myWebView!!.loadUrl(url)
        myWebView!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
        return myWebView
    }
}