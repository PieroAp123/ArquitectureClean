package com.pjap.data.network.entities

import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.User

data class LoginResponse (
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("lastname")
    val lastname: String,
    @field:SerializedName("phone")
    val phone: String,

) {
    companion object{
        fun toLogin(loginResponse: LoginResponse?): User {
            if (loginResponse?.success != null) {
                return User(
                    loginResponse.email, loginResponse.name,
                    loginResponse.lastname, loginResponse.phone)
            }
            return User("", "", "", "")
        }
    }
}