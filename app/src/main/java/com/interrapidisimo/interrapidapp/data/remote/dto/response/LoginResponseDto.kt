package com.interrapidisimo.interrapidapp.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("Usuario") val username: String,
    @SerializedName("Identificacion") val idNumber: String,
    @SerializedName("Nombre") val name: String
)