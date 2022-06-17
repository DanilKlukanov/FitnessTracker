package com.example.fitnesstracker.network.dto

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)