package com.u3coding.cactus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.u3coding.cactus.api.Api
import com.u3coding.cactus.api.LoginBean
import com.u3coding.cactus.base.BaseActivity
import com.u3coding.cactus.login.LoginActivity
import com.u3coding.cactus.rategame.WebViewActivity
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
        list.add(244210)
        list.add(271590)
        var idlist :ArrayList<Int> = ArrayList()
        idlist.add(1)
        idlist.add(4543)
        var mintent: Intent = Intent(this,WebViewActivity::class.java)

        mintent.putExtra(SIDLIStNAME,list)
        mintent.putExtra(IDLIStNAME,idlist)

        startActivity(mintent)
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