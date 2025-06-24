package com.interrapidisimo.interrapidapp.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("Mac") val mac: String = "",
    @SerializedName("NomAplicacion") val appName: String = "Controller APP",
    @SerializedName("Password") val password: String,
    @SerializedName("Path") val path: String = "",
    @SerializedName("Usuario") val username: String
)
