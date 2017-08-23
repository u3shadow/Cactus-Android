package com.u3coding.cactus.rategame

import android.app.Fragment
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.RatingBar
import android.widget.ViewFlipper
import com.u3coding.cactus.R
import com.u3coding.cactus.api.Api
import com.u3coding.cactus.api.GetGameBean
import com.u3coding.cactus.base.BaseFragment
import retrofit2.Call
import retrofit2.Response


/**
 * Created by u3-linux on 8/23/17.
 */
class FindGameFragment : BaseFragment(),View.OnClickListener {
    private var flipper: ViewFlipper?=null
    private val baseUrl:String = "http://store.steampowered.com/app/"
    private var nextBt: Button? = null
    private var passBt:Button? = null
    private var ratBar: RatingBar?=null
    private var finishBt: Button?= null
    private var rateMap:HashMap<Int, Int> = hashMapOf()
    private var idList = listOf<Int>()
    private var sidList= listOf<Int>()
    private var index:Int=0
    private var pref:SharedPreferences?=null
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       getActivity().setTitle(getResources().getString(R.string.findgame))
       var view =  inflater.inflate(R.layout.webview_activity, null)
       pref = activity.getSharedPreferences("login",1)
       initView(view)
       getGameApi()
       return view
    }


    override fun initView(view:View) {
        flipper = view.findViewById(R.id.flipper)
        nextBt = view.findViewById(R.id.next_bt)
        passBt = view.findViewById(R.id.pass_bt)
        finishBt = view.findViewById(R.id.finish_bt)
        ratBar = view.findViewById(R.id.rating_bar)
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
            nextBt?.visibility= View.INVISIBLE
            passBt?.visibility= View.INVISIBLE
            finishBt?.setOnClickListener(object :View.OnClickListener{
                override fun onClick(view: View?) {
                var start = ratBar!!.rating.toInt()
                rateMap.put(idList.get(index),start as Int)
                calGameApi()
                }

            })
        }
        //change to finish
        flipper!!.showNext()
        var web = flipper!!.currentView as WebView
        web.reload()
    }
    fun calGameApi(){

    }
    fun getGameApi(){
        var api = Api()
        var call = api.getService().getGames(pref?.getString("userid","-1").toString())
        call.enqueue(object :retrofit2.Callback<GetGameBean>{
            override fun onResponse(call: Call<GetGameBean>?, response: Response<GetGameBean>?) {
               if (response!!.isSuccessful){
                   idList = response.body()?.id!!
                   sidList = response.body()?.sid!!
               }
               initLis()
            }
            override fun onFailure(call: Call<GetGameBean>?, t: Throwable?) {
                showToast(getString(R.string.api_error))
            }

        })

    }
    private fun addWebView(url: String): WebView {

      var  myWebView:WebView = WebView(activity)
        val w = myWebView.getSettings()
        myWebView.webChromeClient = WebChromeClient()
        w.setPluginState(WebSettings.PluginState.ON)
        w.javaScriptEnabled = true
        var m = MyWebClient(activity)
        myWebView!!.setWebViewClient(m)
        myWebView.loadUrl(url)
        return myWebView
    }
}
