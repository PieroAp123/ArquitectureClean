package com.pjap.deliveryaplication.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pjap.deliveryaplication.MainActivity
import com.pjap.deliveryaplication.databinding.LoginActivityBinding
import com.pjap.deliveryaplication.screens.register.RegisterActivity

class LoginActivity: AppCompatActivity() {

    private lateinit var loginBinding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        supportActionBar?.hide()
        loginBinding.buttonLogin.setOnClickListener { goToMaiActivity() }
        loginBinding.formRegister.setOnClickListener {  goToRegister() }
    }

    private fun goToMaiActivity() {
        val intentMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentMainActivity)
    }

    private fun goToRegister() {
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
    }

}