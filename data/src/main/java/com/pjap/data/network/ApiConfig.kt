package com.pjap.data.network

import com.pjap.data.network.entities.LoginResponse
import com.pjap.data.network.entities.RegisterResponse
import com.pjap.data.network.entities.UserResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiConfig {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun doLogin(@Body params: RequestBody): Call<LoginResponse>

    @GET("getAll")
    fun getUsers(): Call<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun registerUser(@Body params: RequestBody): Call<RegisterResponse>
}