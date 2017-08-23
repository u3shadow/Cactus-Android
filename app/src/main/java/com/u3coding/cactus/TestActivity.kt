package com.u3coding.cactus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.u3coding.cactus.api.Api
import com.u3coding.cactus.api.LoginBean
import com.u3coding.cactus.base.BaseActivity
import retrofit2.Call
import retrofit2.Response

/**
 * Created by u3 on 17-8-17.
 */
class TestActivity: BaseActivity(),View.OnClickListener{
    override fun onClick(p0: View?) {
        test()
    }

    val IDLIStNAME:String="idlist"
    val SIDLIStNAME:String="sidlist"
    override fun initLis() {
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        var list :ArrayList<Int> = ArrayList()
        list.add(205270)
        list.add(13200)
        list.add(281260)
        list.add(344900)
        list.add(429490)
        list.add(402450)
        list.add(289200)
        list.add(393410)
        list.add(451100)
        list.add(269810)
        var idlist :ArrayList<Int> = ArrayList()
        idlist.add(5871)
        idlist.add(1556)
        idlist.add(8516)
        idlist.add(12363)
        idlist.add(9664)
        idlist.add(6262)
        idlist.add(5709)
        idlist.add(1387)
        idlist.add(8606)
        idlist.add(5297)
/*        var mintent: Intent = Intent(this,WebViewActivity::class.java)

        mintent.putExtra(SIDLIStNAME,list)
        mintent.putExtra(IDLIStNAME,idlist)

        startActivity(mintent)*/
        var test : Button= findViewById(R.id.test)
        test.setOnClickListener(this)

    }
    fun test(){
        var api = Api()
        var map = hashMapOf<String,String>()
        map.put("name","u3shadow2")
        map.put("psw","1")
        var call = api.getService().login("u3shadow2","1")
        call.enqueue(object :retrofit2.Callback<LoginBean>{
            override fun onResponse(call: Call<LoginBean>?, response: Response<LoginBean>?) {
                Log.i("1",response?.body()?.userid)

            }
            override fun onFailure(call: Call<LoginBean>?, t: Throwable?) {

            }

        })

    }

}