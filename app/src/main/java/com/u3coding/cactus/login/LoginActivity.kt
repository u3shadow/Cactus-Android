package com.u3coding.cactus.login

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.u3coding.cactus.api.Api
import com.u3coding.cactus.api.LoginBean
import com.u3coding.cactus.R
import retrofit2.Call
import retrofit2.Response

class LoginActivity: Activity(), View.OnClickListener{

    private var userNameEt:EditText? =  null
    private var pwEt:EditText? = null
    private var loginBt: Button? = null
    private var signupBt:TextView? = null
    var pref:SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.statusBarColor = resources.getColor(R.color.holo_blue_dark)
        setContentView(R.layout.login_activity)
        pref = getSharedPreferences("login",1)
        initView()
        initLis()

    }
    fun initView(){
        userNameEt = findViewById(R.id.username_et)
        pwEt =  findViewById(R.id.pw_et)
        loginBt = findViewById(R.id.login_bt)
        signupBt = findViewById(R.id.signup_tv)
    }
    fun initLis(){
        loginBt!!.setOnClickListener(this)
        signupBt!!.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when (view!!.id){
            loginBt!!.id  -> loginClick()
            signupBt!!.id -> signupClick()
        }
    }
    fun signupClick(){
        var intent = Intent(this, SignupActivity::class.java)
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
            loginApi()
        }
    }
    fun loginApi(){
        var api = Api()
        var call = api.getService().login(userNameEt!!.text.toString(),pwEt!!.text.toString())
        call.enqueue(object :retrofit2.Callback<LoginBean>{
            override fun onResponse(call: Call<LoginBean>?, response: Response<LoginBean>?) {
               if (response!!.isSuccessful){
                  var code =  response.body()!!.code
                   when(code){
                       200 -> {
                           var id = response.body()!!.userid
                           pref?.edit()?.putString("userid",id)?.apply()
                           jumpToMain()
                       }
                       230 -> errorToast(getString(R.string.wrong_login_info))
                       231 -> errorToast(getString(R.string.api_error))
                   }
               }
            }
            override fun onFailure(call: Call<LoginBean>?, t: Throwable?) {

            }

        })

    }
    fun errorToast(errors:String){
        Toast.makeText(this,errors,Toast.LENGTH_LONG).show()
    }
    fun jumpToMain(){
      // var mIntent:Intent = Intent(this,MainActivity::class)
       //startActivity(mIntent)
    }
}
