package com.pjap.data.network.repository

import com.pjap.data.network.ApiConfig
import com.pjap.data.network.entities.UserResponse
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.HttpError
import com.pjap.domain.entities.User
import com.pjap.domain.entities.Users
import com.pjap.domain.repository.UserRepositoryNetwork

class UserRepository(private val apiConfig: ApiConfig): UserRepositoryNetwork {

    override fun getUser(): CustomResult<List<User>> {
        val callApi = apiConfig.getUsers().execute()
        return when(callApi.isSuccessful) {
            true -> {
                val response: UserResponse? = callApi.body()
                CustomResult.OnSuccess(UserResponse.run { toUser(response) })
            }
            else -> {
                CustomResult.OnError(
                    HttpError(code = callApi.code(), message = callApi.message())
                )
            }
        }
    }

}