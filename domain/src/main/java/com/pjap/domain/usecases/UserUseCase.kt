package com.pjap.domain.usecases

import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.User
import com.pjap.domain.repository.UserRepositoryNetwork

class UserUseCase(
    private val userRepositoryNetwork: UserRepositoryNetwork
) {
     fun getUser(): CustomResult<List<User>> {
        return userRepositoryNetwork.getUser()
     }
}