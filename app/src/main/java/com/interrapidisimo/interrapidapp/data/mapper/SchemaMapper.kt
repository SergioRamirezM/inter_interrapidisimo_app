package com.interrapidisimo.interrapidapp.data.mapper

import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity
import com.interrapidisimo.interrapidapp.data.remote.dto.response.SchemaResponseDto

fun SchemaResponseDto.toEntity(): SchemaEntity = SchemaEntity(
    tableName = tableName,
    remotePrimaryKey = remotePrimaryKey,
    createQuery = createQuery,
    batchSize = batchSize,
    filter = filter,
    error = error,
    numberOfFields = numberOfFields,
    methodApp = methodApp,
    lastSyncDate = lastSyncDate
)