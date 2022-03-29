package com.pjap.domain.usecases

import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User
import com.pjap.domain.repository.LoginRepositoryNetwork
import com.pjap.domain.repository.LoginRepositoryPreference

open class LoginUseCase(
    private val loginRepositoryNetwork: LoginRepositoryNetwork,
    private val loginRepositoryPreference: LoginRepositoryPreference
) {

    open fun doLogin(username: String, password: String): CustomResult<User> {
        val user = loginRepositoryNetwork.doLogin(username, password)
        when (user) {
            is CustomResult.OnSuccess -> {
                loginRepositoryPreference.run {
                    saveUser(user.data)
                    saveIsLogin(true)
                }
            }
        }
        return user
    }


    fun isUserLogin(): Boolean {
        return loginRepositoryPreference.isLogin()
    }

    fun getUSer(): User {
        return loginRepositoryPreference.getUser()
    }

}