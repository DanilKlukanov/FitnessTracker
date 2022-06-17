package com.example.fitnesstracker.network.dto

import com.google.gson.annotations.SerializedName

data class Gender(
    @SerializedName("code")
    val code: Int,
    @SerializedName("name")
    val name: String
)