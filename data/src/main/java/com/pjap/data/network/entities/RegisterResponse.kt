package com.pjap.data.network.entities

import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.RegisterDetail

data class RegisterResponse (
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: String,
) {
    companion object{
        fun toMessageRegister(responseRegister: RegisterResponse?): RegisterDetail {
            if (responseRegister!!.success) {
                return RegisterDetail(responseRegister.message)
            }
            return RegisterDetail("Response detail register")
        }
    }
}
