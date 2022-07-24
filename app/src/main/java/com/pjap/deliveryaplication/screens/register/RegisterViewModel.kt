package com.pjap.deliveryaplication.screens.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pjap.deliveryaplication.utils.DispatcherProvider
import com.pjap.domain.entities.CustomError
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.RegisterDetail
import com.pjap.domain.extensions.toError
import com.pjap.domain.usecases.RegisterUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val dispatcherProvider: DispatcherProvider
): ViewModel() {

    val registerLoadingLiveData = MutableLiveData<Boolean>()
    val registerErrorLiveData = MutableLiveData<CustomError>()
    val registerLiveData = MutableLiveData<RegisterDetail>()

    fun doRegister(email: String, name: String, lastname: String, phone: String, image: String, password: String, created_at: String, updated_at: String) {
        GlobalScope.launch(dispatcherProvider.IO + CoroutineExceptionHandler { _, ex ->
            registerErrorLiveData.postValue(ex.toError())
            registerLoadingLiveData.postValue(false)
        }) {
            registerLoadingLiveData.postValue(true)
            when(val register = registerUseCase.doRegister(email, name, lastname, phone, image, password, created_at, updated_at)) {
                is CustomResult.OnSuccess -> {
                    registerLiveData.postValue(register.data)
                }
                is CustomResult.OnError -> {
                    registerErrorLiveData.postValue(register.error)
                }
            }
        }
    }

}