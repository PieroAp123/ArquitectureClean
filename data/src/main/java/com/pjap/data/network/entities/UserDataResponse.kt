package com.pjap.data.network.entities

import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.User

data class UserDataResponse(
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("lastname")
    val lastname: String,
    @field:SerializedName("phone")
    val phone: String
)
