package com.pjap.deliveryaplication.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pjap.data.network.entities.UserDataResponse
import com.pjap.data.network.entities.UserResponse
import com.pjap.deliveryaplication.utils.DispatcherProvider
import com.pjap.domain.entities.CustomError
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User
import com.pjap.domain.entities.Users
import com.pjap.domain.extensions.toError
import com.pjap.domain.usecases.UserUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userUseCase: UserUseCase,
    private val dispatcherProvider: DispatcherProvider = DispatcherProvider()
): ViewModel() {

    val userLoadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<CustomError>()
    val usersLiveData = MutableLiveData<User>()
    lateinit var listUser: List<UserResponse>

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllUsers() {
        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, ex ->
            errorLiveData.postValue(ex.toError())
            userLoadingLiveData.postValue(false)
        }) {
            userLoadingLiveData.postValue(true)
            when (val user = userUseCase.getUser()) {
                is CustomResult.OnSuccess -> {
                    usersLiveData.postValue(user.data)
                    Log.e("logUser", user.toString())
                }
                is CustomResult.OnError -> {
                    errorLiveData.postValue(user.error)
                }
            }
        }
    }

}