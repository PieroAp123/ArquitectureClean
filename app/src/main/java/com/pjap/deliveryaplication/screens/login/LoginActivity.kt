package com.pjap.deliveryaplication.screens.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pjap.deliveryaplication.MainActivity
import com.pjap.deliveryaplication.databinding.LoginActivityBinding
import com.pjap.deliveryaplication.screens.register.RegisterActivity
import com.pjap.domain.entities.CustomError
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity: AppCompatActivity() {

    private lateinit var loginBinding: LoginActivityBinding
    private val loginViewModel: LoginViewModel by viewModel(clazz = LoginViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        supportActionBar?.hide()
        iniObservers()
        loginBinding.buttonLogin.setOnClickListener { goToMaiActivity() }
        loginBinding.formRegister.setOnClickListener {  goToRegister() }
    }

    private fun iniObservers() {
        loginViewModel.userLoadingLiveData.observe(this) {
            Log.e("LoginActivity", "$it") }
        loginViewModel.errorLiveData.observe(this) {
            showError(it)
        }
        loginViewModel.usersLiveData.observe(this) {
            Log.d("LoginActivity", "$it")
        }
        loginViewModel.getAllUsers()
    }

    private fun showError(error: CustomError) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
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