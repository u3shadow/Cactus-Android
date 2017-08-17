package com.u3coding.cactus

import android.content.Intent
import android.os.Bundle

/**
 * Created by u3 on 17-8-17.
 */
class TestActivity:BaseActivity(){
    val IDLIStNAME:String="idlist"
    val SIDLIStNAME:String="sidlist"
    override fun initLis() {
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var list :ArrayList<Int> = ArrayList()
        list.add(632920)
        list.add(342350)
        var idlist :ArrayList<Int> = ArrayList()
        idlist.add(1)
        idlist.add(4543)
        var mintent: Intent = Intent(this,WebViewActivity::class.java)

        mintent.putExtra(SIDLIStNAME,list)
        mintent.putExtra(IDLIStNAME,idlist)
        startActivity(mintent)
    }

}