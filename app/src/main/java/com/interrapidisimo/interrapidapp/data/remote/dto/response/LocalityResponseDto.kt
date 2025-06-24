package com.interrapidisimo.interrapidapp.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class LocalityResponseDto(
    @SerializedName("AbreviacionCiudad") val abbreviation: String,
    @SerializedName("NombreCompleto") val fullName: String
)
