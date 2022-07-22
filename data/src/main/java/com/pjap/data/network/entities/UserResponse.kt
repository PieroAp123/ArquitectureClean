package com.pjap.data.network.entities

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.pjap.domain.entities.User
import com.pjap.domain.entities.Users

data class UserResponse (
    @field:SerializedName("users")
    val users: List<UserDataResponse>
)  {
    companion object {
        fun toUser(response: UserResponse?): User {
            Log.e("Respuesta",response.toString())
            if (response!!.users.isNotEmpty()){
                response.users.forEach {
                    return User(it.email, it.name, it.lastname, it.phone)
                }
            }
            return User("Juan Perez", "", "", "")
        }
    }
}
/*{
    companion object {
        fun toUser(response: UserResponse?): User {
            Log.e("Respuesta",response.toString())
            if (response!!.users.isNotEmpty()){
                response.users.forEach {
                    return User(it.email, it.name, it.lastname, it.phone)
                }
            }
            return User("Juan Perez", "", "", "")
        }
    }
}*/ /*{
    companion object {
        fun toUser(response: UserResponse?): User {
            Log.e("Respuesta",response.toString())
            if (response!!.users.isNotEmpty()){
                response.users.forEach {
                    return User(it.email, it.name, it.lastname, it.phone)
                }
            }
            return User("Juan Perez", "", "", "")
        }
    }
}*/
/*{
    companion object{
        fun toUser(response: List<UserResponse>?): User {
            if (response!!.isNotEmpty()) {
                listOf<User>()
            }
            return listOf<User>()
        }
    }
}*/
