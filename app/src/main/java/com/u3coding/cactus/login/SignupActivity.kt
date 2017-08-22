package com.u3coding.cactus.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.u3coding.cactus.base.BaseActivity
import com.u3coding.cactus.R

/**
 * Created by u3 on 17-8-16.
 */
class SignupActivity: BaseActivity(),View.OnClickListener{


    var userNameEt:EditText?= null
    var pwEt:EditText?= null
    var pwvEt:EditText?= null
    var emailEt:EditText?= null
    var signupBt:Button?=null
    override fun onClick(view: View?) {
        when (view!!.id){
            signupBt!!.id -> signupClick()
        }
    }
    fun signupClick(){
        //api

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.statusBarColor = resources.getColor(R.color.holo_blue_dark)
        setContentView(R.layout.signup_activity)
        initView()
        initLis()
    }
    override fun initView(){
        userNameEt = findViewById(R.id.username_et)
        pwEt = findViewById(R.id.psw_et)
        pwvEt = findViewById(R.id.psw_confirm_et)
        emailEt = findViewById(R.id.email_et)
        signupBt=findViewById(R.id.signup_bt)
    }

    override fun initLis() {
        signupBt!!.setOnClickListener(this)
    }
}