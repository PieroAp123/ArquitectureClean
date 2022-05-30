package com.pjap.deliveryaplication.screens.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pjap.deliveryaplication.databinding.RegisterActivityBinding
import com.pjap.deliveryaplication.screens.login.LoginActivity

class RegisterActivity: AppCompatActivity() {

    private lateinit var registerBinding: RegisterActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        supportActionBar?.hide()
        registerBinding.buttonRegister.setOnClickListener { goToLogin() }

    }

    private fun goToLogin() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }
}