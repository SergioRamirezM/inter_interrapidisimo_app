package com.interrapidisimo.interrapidapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "schema_table",
    indices = [ Index(value = ["table_name"], unique = true) ]
)
data class SchemaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "table_name")
    val tableName: String,

    @ColumnInfo(name = "remote_primary_key")
    val remotePrimaryKey: String,

    @ColumnInfo(name = "create_query")
    val createQuery: String,

    @ColumnInfo(name = "batch_size")
    val batchSize: Int,

    @ColumnInfo(name = "filter")
    val filter: String?,

    @ColumnInfo(name = "error")
    val error: String?,

    @ColumnInfo(name = "number_of_fields")
    val numberOfFields: Int,

    @ColumnInfo(name = "method_app")
    val methodApp: String?,

    @ColumnInfo(name = "last_sync_date")
    val lastSyncDate: String
)
