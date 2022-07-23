package com.pjap.domain.repository

import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User
import com.pjap.domain.entities.Users

interface UserRepositoryNetwork {
    fun getUser(): CustomResult<List<User>>
}