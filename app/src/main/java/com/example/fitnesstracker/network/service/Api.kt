package com.example.fitnesstracker.network.service

import com.example.fitnesstracker.network.dto.Login
import com.example.fitnesstracker.network.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("/api/auth/register")
    fun register(
        @Query("login") login: String,
        @Query("password") password: String,
        @Query("name") name: String,
        @Query("gender") gender: Int
    ): Call<Login>

    @POST("/api/auth/login")
    fun login(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<Login>

    @POST("/api/auth/logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<Void>

    @GET("/api/user/profile")
    fun profile(
        @Header("Authorization") token: String
    ): Call<User>

}