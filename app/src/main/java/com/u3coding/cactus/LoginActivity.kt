/*package com.u3coding.cactus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/**
 * Created by u3 on 17-8-14.
 */
class LoginActivity: Activity(), View.OnClickListener{
    override fun onClick(view: View?) {
        when (view!!.id){
            loginBt!!.id  -> loginClick()
            signupBt!!.id -> signupClick()
        }
    }
    fun signupClick(){
        var intent = Intent(this,SignupActivity::class.java)
        startActivity(intent)
    }
    fun loginClick(){
        var isEnoughInfo:Boolean = false
        if (userNameEt?.text != null&&pwEt?.text != null){
            isEnoughInfo = true
        }
        else{
            Toast.makeText(this,getString(R.string.not_enough_login_info),Toast.LENGTH_LONG).show()
            return
        }
        if (isEnoughInfo){
            //api
        }
    }

    private var userNameEt:EditText? =  null
    private var pwEt:EditText? = null
    private var loginBt:Button? = null
    private var signupBt:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        initView()
        initLis()

    }
    fun initView(){
        userNameEt = findViewById(R.id.username_et) as EditText
        pwEt =  findViewById(R.id.pw_et)as EditText
        loginBt = findViewById(R.id.login_bt)as Button
        signupBt = findViewById(R.id.signup_bt)as Button
    }
    fun initLis(){
        loginBt!!.setOnClickListener(this)
        loginBt!!.setOnClickListener(this)
    }

}*/
