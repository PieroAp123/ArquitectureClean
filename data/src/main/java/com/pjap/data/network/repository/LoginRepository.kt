package com.pjap.data.network.repository

import com.pjap.data.network.ApiConfig
import com.pjap.data.network.entities.LoginResponse
import com.pjap.domain.entities.CustomNotFoundError
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.HttpError
import com.pjap.domain.entities.User
import com.pjap.domain.repository.LoginRepositoryNetwork
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginRepository(private val apiConfig: ApiConfig): LoginRepositoryNetwork  {
    override fun doLogin(email: String, password: String): CustomResult<User> {
        val paramObject = JSONObject()
        paramObject.put("email", email)
        paramObject.put("password", password)

        val params = paramObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val callApi = apiConfig.doLogin(params).execute()
        return when (callApi.isSuccessful) {
            true -> {
                val responseLogin: LoginResponse? = callApi.body()
                val detailResponseLogin = responseLogin?.message
                if (responseLogin!!.success) {
                    CustomResult.OnSuccess(LoginResponse.run { toLogin(responseLogin) })
                } else {
                    CustomResult.OnError(CustomNotFoundError(message = detailResponseLogin))
                }

            }
            else -> {
                CustomResult.OnError(
                    HttpError(code = callApi.code(), message = callApi.message())
                )
            }
        }

    }
}