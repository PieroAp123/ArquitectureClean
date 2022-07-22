package com.pjap.data.network.entities

import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.User

data class LoginResponse (
    @field:SerializedName("user")
    val user: UserResponse? = null,
)