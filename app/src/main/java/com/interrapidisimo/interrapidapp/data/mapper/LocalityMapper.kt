package com.interrapidisimo.interrapidapp.data.mapper

import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity
import com.interrapidisimo.interrapidapp.data.remote.dto.response.LocalityResponseDto

fun LocalityResponseDto.toEntity(): LocalityEntity = LocalityEntity(
    abbreviation = abbreviation,
    fullName = fullName
)