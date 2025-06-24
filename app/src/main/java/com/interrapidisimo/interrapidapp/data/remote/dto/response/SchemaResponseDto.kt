package com.interrapidisimo.interrapidapp.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class SchemaResponseDto(
    @SerializedName("NombreTabla")
    val tableName: String,

    @SerializedName("Pk")
    val remotePrimaryKey: String,

    @SerializedName("QueryCreacion")
    val createQuery: String,

    @SerializedName("BatchSize")
    val batchSize: Int,

    @SerializedName("Filtro")
    val filter: String?,

    @SerializedName("Error")
    val error: String?,

    @SerializedName("NumeroCampos")
    val numberOfFields: Int,

    @SerializedName("MetodoApp")
    val methodApp: String?,

    @SerializedName("FechaActualizacionSincro")
    val lastSyncDate: String
)
