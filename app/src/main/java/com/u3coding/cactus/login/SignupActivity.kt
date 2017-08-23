package com.u3coding.cactus.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.u3coding.cactus.base.BaseActivity
import com.u3coding.cactus.R
import com.u3coding.cactus.api.Api
import com.u3coding.cactus.api.SignupBean
import retrofit2.Call
import retrofit2.Response

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
        if (userNameEt?.text.toString().isNotEmpty()&&
                emailEt?.text.toString().isNotEmpty()&&
                pwEt?.text.toString().isNotEmpty()&&pwvEt?.text.toString().isNotEmpty()) {
            if(pwEt?.text.toString().equals(pwvEt?.text.toString())){
                signupApi()
            }
            else{
                showToast(getString(R.string.wrong_pw))
            }
        }else{
            showToast(getString(R.string.not_enough_info))
        }
    }
    fun signupApi(){
        var api = Api()
        var call = api.getService().signup(userNameEt!!.text.toString(),pwEt!!.text.toString(),emailEt!!.text.toString())
        call.enqueue(object :retrofit2.Callback<SignupBean>{
            override fun onResponse(call: Call<SignupBean>?, response: Response<SignupBean>?) {
               if (response!!.isSuccessful){
                  var code =  response.body()!!.code
                   when(code){
                       200 -> {
                           showToast(getString(R.string.signup_success))
                           jumpToLogin()
                       }
                       230 -> showToast(getString(R.string.name_exist))
                       231 -> showToast(getString(R.string.email_exist))
                   }
               }
            }
            override fun onFailure(call: Call<SignupBean>?, t: Throwable?) {
                showToast(getString(R.string.api_error))
            }

        })

    }

    fun jumpToLogin(){
      var mIntent = Intent(this,LoginActivity::class.java)
      startActivity(mIntent)
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