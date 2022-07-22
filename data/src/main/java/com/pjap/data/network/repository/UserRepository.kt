package com.pjap.data.network.repository

import com.pjap.data.network.ApiConfig
import com.pjap.data.network.entities.UserResponse
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.HttpError
import com.pjap.domain.entities.User
import com.pjap.domain.entities.Users
import com.pjap.domain.repository.UserRepositoryNetwork

class UserRepository(private val apiConfig: ApiConfig): UserRepositoryNetwork {

    override fun getUser(): CustomResult<User> {
        /*val callApi: Call<UserResponse> = apiConfig.getUsers()
        callApi.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                callApi.execute().body()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                CustomResult.OnError<User>(
                    HttpError(code = callApi.execute().code(), message = callApi.execute().message())
                )
            }

        })*/
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