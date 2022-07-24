package com.pjap.deliveryaplication.screens.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pjap.deliveryaplication.databinding.RegisterActivityBinding
import com.pjap.deliveryaplication.screens.login.LoginActivity
import com.pjap.domain.entities.CustomError
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity: AppCompatActivity() {

    private lateinit var registerBinding: RegisterActivityBinding
    private val registerViewModel: RegisterViewModel by viewModel(clazz = RegisterViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        supportActionBar?.hide()
        registerBinding.buttonRegister.setOnClickListener {
            registerViewModel.doRegister(
                registerBinding.editTextEmail.text.toString(), registerBinding.editTextName.text.toString(), registerBinding.editTextLastName.text.toString(),
                registerBinding.editTextPhone.text.toString(), registerBinding.editTextImage.text.toString(), registerBinding.editTextPassword.text.toString(), "", "")
            this.iniObserversRegister()
        }
        registerBinding.buttonToLogin.setOnClickListener { goToLogin() }
    }

    private fun goToLogin() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }

    private fun iniObserversRegister() {
        registerViewModel.registerLoadingLiveData.observe(this) {
            Log.e("RegisterActivity", "$it") }
        registerViewModel.registerErrorLiveData.observe(this) {
            showErrorRegister(it)
        }
        registerViewModel.registerLiveData.observe(this){
            Log.d("RegisterActivityLiveData", "$it")
        }
    }

    private fun showErrorRegister(error: CustomError) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
    }

}
