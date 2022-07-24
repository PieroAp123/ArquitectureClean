package com.pjap.data.network.repository

import com.pjap.data.network.ApiConfig
import com.pjap.data.network.entities.RegisterResponse
import com.pjap.domain.entities.CustomResult
import com.pjap.domain.entities.HttpError
import com.pjap.domain.entities.RegisterDetail
import com.pjap.domain.repository.RegisterRepositoryNetwork
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RegisterRepository(private val apiConfig: ApiConfig): RegisterRepositoryNetwork {
    override fun doRegister(email: String, name: String, lastname: String, phone: String, image: String, password: String, created_at: String, updated_at: String
    ): CustomResult<RegisterDetail> {
        val paramObject =JSONObject()
        paramObject.put("email", email)
        paramObject.put("name", name)
        paramObject.put("lastname", lastname)
        paramObject.put("phone", phone)
        paramObject.put("image", image)
        paramObject.put("password", password)
        paramObject.put("created_at", "")
        paramObject.put("updated_at", "")

        val params = paramObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val callApi = apiConfig.registerUser(params).execute()
        return when(callApi.isSuccessful) {
            true -> {
                val responseRegister: RegisterResponse? = callApi.body()
                CustomResult.OnSuccess(RegisterResponse.run { toMessageRegister(responseRegister) })
            }
            else -> {
                CustomResult.OnError(
                    HttpError(code = callApi.code(), message = callApi.message())
                )
            }
        }
    }


}