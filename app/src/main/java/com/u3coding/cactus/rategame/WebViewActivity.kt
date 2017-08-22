package com.u3coding.cactus.rategame
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ViewFlipper
import android.webkit.WebViewClient
import android.webkit.WebSettings.PluginState
import android.webkit.WebChromeClient
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import com.u3coding.cactus.base.BaseActivity
import com.u3coding.cactus.R
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.webkit.WebResourceRequest


open class WebViewActivity: BaseActivity(),View.OnClickListener{

    val IDLIStNAME:String="idlist"
    val SIDLIStNAME:String="sidlist"
    private var flipper:ViewFlipper?=null
    private val baseUrl:String = "http://store.steampowered.com/app/"
    private var nextBt:Button? = null
    private var passBt:Button? = null
    private var ratBar:RatingBar?=null
    private var rateMap:HashMap<Int, Int> = hashMapOf()
    private var idList = listOf<Int>()
    private var sidList= listOf<Int>()
    private var index:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.statusBarColor =Color.BLACK
        setContentView(R.layout.webview_activity)
        idList = intent.getIntegerArrayListExtra(IDLIStNAME)
        sidList = intent.getIntegerArrayListExtra(SIDLIStNAME)
        index = 0
        initView()
        initLis()
    }
    override fun initView() {
        flipper = findViewById(R.id.flipper)
        nextBt = findViewById(R.id.next_bt)
        passBt = findViewById(R.id.pass_bt)
        ratBar = findViewById(R.id.rating_bar)
    }

    override fun initLis() {
        for (item in sidList){
            flipper!!.addView(addWebView(baseUrl+item));
        }
        nextBt!!.setOnClickListener(this)
        passBt!!.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when (view!!.id){
            nextBt!!.id ->showNext(ratBar!!.rating)
            passBt!!.id -> showNext(0 as Float)
        }
    }
    fun showNext(score:Float){
        var start = score.toInt()
        rateMap.put(idList.get(index),start as Int)
        index++
        if (index == 9){
            nextBt?.setText(R.string.finish)
            nextBt?.setOnClickListener(object :View.OnClickListener{
                override fun onClick(view: View?) {
                    getGameApi()
                }

            })
        }
        //change to finish
        flipper!!.showNext()
        var web = flipper!!.currentView as WebView
        web.reload()
    }
    fun getGameApi(){

    }
    private fun addWebView(url: String): WebView {

      var  myWebView:WebView = WebView(this)
        val w = myWebView.getSettings()
        myWebView.webChromeClient = WebChromeClient()
        w.setPluginState(PluginState.ON)
        w.javaScriptEnabled = true
        var m = MyWebClient(this)
        myWebView!!.setWebViewClient(m)
        myWebView.loadUrl(url)
        return myWebView
    }
}