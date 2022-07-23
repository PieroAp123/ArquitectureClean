package com.pjap.data.network.entities

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.User
import com.pjap.domain.entities.Users

data class UserResponse (
    @field:SerializedName("users")
    val users: List<User>
)  {
    companion object {
        fun toUser(response: UserResponse?): List<User> {
            Log.e("Respuesta",response.toString())
            val listUser = response?.users
            if (response!!.users.isNotEmpty()){
                return listUser!!
            }
            return listUser!!
        }

    }
}
