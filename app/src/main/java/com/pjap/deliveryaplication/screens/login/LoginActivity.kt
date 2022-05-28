package com.pjap.deliveryaplication.screens.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pjap.deliveryaplication.databinding.LoginActivityBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var loginBinding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        supportActionBar?.hide()
    }
}