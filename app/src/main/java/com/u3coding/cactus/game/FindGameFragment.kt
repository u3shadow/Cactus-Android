package com.u3coding.cactus.game

import android.content.SharedPreferences
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.*
import com.google.gson.Gson
import com.u3coding.cactus.R
import com.u3coding.cactus.api.Api
import com.u3coding.cactus.api.CalGameBean
import com.u3coding.cactus.api.GetGameBean
import com.u3coding.cactus.base.BaseFragment
import retrofit2.Call
import retrofit2.Response


/**
 * Created by u3-linux on 8/23/17.
 */
class FindGameFragment : BaseFragment(),View.OnClickListener {
    private var webView: WebView?=null
    private var foWeb: WebView?=null
    private val baseUrl:String = "http://store.steampowered.com/app/"
    private var nextBt: Button? = null
    private var ratBar: RatingBar?=null
    private var finishBt: Button?= null
    private var pb:ProgressBar?=null
    private var fl:FrameLayout?=null
    private var loadTv: TextView?=null

    private var rateMap:HashMap<String, Int> = hashMapOf()
    private var idList = listOf<Int>()
    private var sidList= listOf<Int>()
    private var pref:SharedPreferences?=null
    private var index:Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       getActivity().setTitle(getResources().getString(R.string.findgame))
       var view =  inflater.inflate(R.layout.findgame_fragment, null)
       index = 0
       pref = activity.getSharedPreferences("login",1)
       initView(view)
       getGameApi()
       return view
    }
    override fun initView(view:View) {
        webView = view.findViewById(R.id.web_wv)
        foWeb = view.findViewById(R.id.fore_wv)
        nextBt = view.findViewById(R.id.next_bt)
        finishBt = view.findViewById(R.id.finish_bt)
        ratBar = view.findViewById(R.id.rating_bar)
        pb = view.findViewById(R.id.pro_pb)
        fl = view.findViewById(R.id.main_fl)
        loadTv = view.findViewById(R.id.loading_tv)
    }

    override fun initLis() {
        webView = initWebView(this!!.webView!!)
        foWeb = initWebView(this!!.foWeb!!)
        nextBt!!.setOnClickListener(this)
        webView!!.loadUrl(baseUrl+sidList.get(0))
        foWeb!!.loadUrl(baseUrl+sidList.get(1))
        index++
    }
        private fun initWebView(myWebView:WebView): WebView {
        val w = myWebView.getSettings()
        w.setAppCacheEnabled(true);
        w.setDatabaseEnabled(true);
        w.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        w.setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.webChromeClient = WebChromeClient()
        w.setPluginState(WebSettings.PluginState.ON)
        w.javaScriptEnabled = true
        var m = MyGameWebClient(activity)
        myWebView!!.setWebViewClient(m)
        return myWebView
    }
    override fun onClick(view: View?) {
        when (view!!.id){
            nextBt!!.id ->showNext(ratBar!!.rating)
        }
    }
    fun showNext(score:Float){
        var start = score.toInt()
        rateMap.put(idList.get(index!!).toString(),start as Int)
        ratBar!!.rating = 5.toFloat()

        webView!!.loadUrl(baseUrl+sidList.get(index))
        if(index < 9)
        foWeb!!.loadUrl(baseUrl+sidList.get(index+1))
        index++
        if (index == 10){
            turnToFinish()
            index = 0
        }
    }

    private fun turnToFinish() {
        nextBt?.visibility = View.INVISIBLE
        finishBt?.visibility = View.VISIBLE
        finishBt?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var start = ratBar!!.rating.toInt()
                rateMap.put(idList.get(index).toString(), start as Int)
                pb!!.visibility = View.VISIBLE
                fl!!.visibility = View.INVISIBLE
                loadTv!!.visibility = View.VISIBLE
                loadTv!!.text = getString(R.string.calgame)
                finishBt!!.visibility = View.INVISIBLE
                calGameApi()
            }
        })
    }

    fun calGameApi(){
        var api = Api()
        var gson:Gson = Gson()
        var s = gson.toJson(rateMap)
        var call = api.getService().calGame(pref!!.getString("userid","-1"),s)
        call.enqueue(object :retrofit2.Callback<CalGameBean>{
            override fun onFailure(call: Call<CalGameBean>?, t: Throwable?) {
                showToast(getString(R.string.api_error))
                finishBt!!.visibility=View.VISIBLE
            }

            override fun onResponse(call: Call<CalGameBean>?, response: Response<CalGameBean>?) {
                if (response!!.isSuccessful){
                    sidList =  response.body()!!.sid
                    pref!!.edit().putString("sid",gson.toJson(sidList)).apply()
                    var resultFragment = ShowGameFragment()
                    val fragmentTransaction = activity.fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.main_layout, resultFragment)
                    fragmentTransaction.commit()
                }
            }
        }
        )
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

}
