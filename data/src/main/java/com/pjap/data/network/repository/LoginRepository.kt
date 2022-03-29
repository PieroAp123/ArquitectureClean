package com.pjap.data.network.repository

import com.pjap.data.network.ApiConfig
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User
import com.pjap.domain.repository.LoginRepositoryNetwork

class LoginRepository(private val apiConfig: ApiConfig): LoginRepositoryNetwork  {
    override fun doLogin(username: String, password: String): CustomResult<User> {
        TODO("Not yet implemented")
    }
}