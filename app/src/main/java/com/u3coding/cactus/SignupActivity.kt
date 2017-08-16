/*package com.u3coding.cactus

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

/**
 * Created by u3 on 17-8-16.
 */
class SignupActivity:BaseActivity(),View.OnClickListener{


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
        setContentView(R.layout.signup_activity)
        initView()
        initLis()
    }
    override fun initView(){
        userNameEt = findViewById(R.id.username_et)as EditText
        pwEt = findViewById(R.id.psw_et)as EditText
        pwvEt = findViewById(R.id.psw_confirm_et)as EditText
        emailEt = findViewById(R.id.email_et)as EditText
        signupBt=findViewById(R.id.signup_bt)as Button
    }

    override fun initLis() {
        signupBt!!.setOnClickListener(this)
    }
}*/