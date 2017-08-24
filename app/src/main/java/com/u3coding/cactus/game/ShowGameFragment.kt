package com.u3coding.cactus.game

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.*
import com.google.gson.Gson
import com.u3coding.cactus.R
import com.u3coding.cactus.base.BaseFragment

/**
 * Created by u3-linux on 8/23/17.
 */
class ShowGameFragment : BaseFragment(), View.OnClickListener {
    private var webView: WebView?=null
    private val baseUrl:String = "http://store.steampowered.com/app/"
    private var nextBt: Button? = null
    private var sidList= listOf<Int>()
    private var index:Int=0
    private var pref: SharedPreferences?=null
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       getActivity().setTitle(getResources().getString(R.string.ResultGame))
       var view =  inflater.inflate(R.layout.showgame_fragment, null)
       pref = activity.getSharedPreferences("login",1)
       var gson:Gson = Gson()
       sidList = gson.fromJson(pref!!.getString("sid","-1"),ArrayList<Int>()::class.java)
       initView(view)
       initLis()
       return view
    }
    override fun initView(view: View) {
        webView = view.findViewById(R.id.web_wv)
        nextBt = view.findViewById(R.id.next_bt)
    }

    override fun initLis() {
        webView = initWebView(this!!.webView!!)
        nextBt!!.setOnClickListener(this)
        webView!!.loadUrl(baseUrl+sidList.get(0))
    }
    fun initWebView(myWebView: WebView): WebView {
        val w = myWebView.getSettings()
        myWebView.webChromeClient = WebChromeClient()
        w.setPluginState(WebSettings.PluginState.ON)
        w.javaScriptEnabled = true
        var m = MyGameWebClient(activity)
        myWebView!!.setWebViewClient(m)
        return myWebView
    }
    override fun onClick(view: View?) {
        when (view!!.id){
            nextBt!!.id ->showNext()
        }
    }
    fun showNext(){
        index++
        if (index == 10){
            index = 0
        }
        webView!!.loadUrl(baseUrl+sidList.get(index))
    }
}