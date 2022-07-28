package com.mo_samy.retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mo_samy.retrofit.R
import com.mo_samy.retrofit.api.RetrofitFactory
import com.mo_samy.retrofit.models.LoginInfo
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var etEmail : EditText
    lateinit var etPassword :  EditText
    lateinit var btnLogin :  Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        val retrofit = RetrofitFactory().productApi()
        btnLogin.setOnClickListener(View.OnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val call = retrofit.login(LoginInfo(email, pass))
            call.enqueue(object :retrofit2.Callback<LoginInfo>{
                override fun onFailure(call: Call<LoginInfo>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity , "error ${t!!.localizedMessage}" , Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<LoginInfo>?, response: Response<LoginInfo>?) {
                    Toast.makeText(this@MainActivity,"response code : ${response!!.code().toString()}",Toast.LENGTH_LONG).show()
                    Log.d(TAG, "onResponse: success ")
                    finish()
                    startActivity(Intent(this@MainActivity, MainActivity2::class.java))
                }


            })
        })

    }

    private fun initViews() {
        etEmail = findViewById(R.id.editTextTextEmailAddress)
        etPassword = findViewById(R.id.editTextTextPassword)
        btnLogin = findViewById(R.id.button)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}