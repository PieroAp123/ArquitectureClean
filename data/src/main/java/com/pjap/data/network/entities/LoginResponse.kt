package com.pjap.data.network.entities

import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.User

data class LoginResponse (
    @field:SerializedName("user")
    val user: UserResponse? = null,

    @field:SerializedName("status")
    var status: StatusResponse? = null
) {
    companion object {
        fun toUser(response: LoginResponse): User {
            if (response.user == null)
                return User()
            return response.user.toUser()
        }
    }
}

data class UserResponse(
    @field:SerializedName("")
    val name: String? = null,

    @field: SerializedName("")
    val lastname: String? = null,
) {
    fun toUser() = User(name)
}

data class StatusResponse (
    @field: SerializedName("")
    val code: String? = null
)