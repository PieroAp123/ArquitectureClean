package com.pjap.deliveryaplication.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pjap.data.network.entities.UserResponse
import com.pjap.deliveryaplication.utils.DispatcherProvider
import com.pjap.domain.entities.CustomError
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User
import com.pjap.domain.extensions.toError
import com.pjap.domain.usecases.LoginUseCase
import com.pjap.domain.usecases.UserUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userUseCase: UserUseCase,
    private val loginUseCase: LoginUseCase,
    private val dispatcherProvider: DispatcherProvider = DispatcherProvider()
): ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<CustomError>()
    val usersLiveData = MutableLiveData<List<User>>()
    val loginLiveData = MutableLiveData<User>()
    lateinit var listUser: List<UserResponse>

    @OptIn(DelicateCoroutinesApi::class)
    fun doLogin(email: String, password: String) {
        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, ex ->
            errorLiveData.postValue(ex.toError())
            loadingLiveData.postValue(false)
        }) {
            loadingLiveData.postValue(true)
            when( val user = loginUseCase.doLogin(email, password)) {
                is CustomResult.OnSuccess -> {
                    loginLiveData.postValue(user.data)
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(user.error)
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllUsers() {
        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, ex ->
            errorLiveData.postValue(ex.toError())
            loadingLiveData.postValue(false)
        }) {
            loadingLiveData.postValue(true)
            when (val user = userUseCase.getUser()) {
                is CustomResult.OnSuccess -> {
                    usersLiveData.postValue(user.data)
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(user.error)
                }
            }
        }
    }

}