package com.pjap.domain.repository

import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User

interface LoginRepositoryNetwork {
    fun doLogin(email: String, password: String): CustomResult<User>
}