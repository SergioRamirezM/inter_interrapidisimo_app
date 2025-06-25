package com.interrapidisimo.interrapidapp.domain.usecase

import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity
import com.interrapidisimo.interrapidapp.data.mapper.toEntity
import com.interrapidisimo.interrapidapp.data.model.ApiError
import com.interrapidisimo.interrapidapp.data.repository.DatabaseRepository
import com.interrapidisimo.interrapidapp.data.repository.ServiceRepository
import com.interrapidisimo.interrapidapp.util.ErrorHandler
import com.interrapidisimo.interrapidapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class SyncSchemasUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(): Resource<List<SchemaEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = serviceRepository.getSchemas()

                if (!response.isSuccessful) {
                    throw HttpException(response)
                }

                val entities = response.body().orEmpty().map { it.toEntity() }
                databaseRepository.saveSchemas(entities)

                val savedSchemas = databaseRepository.loadSchemas()
                Resource.Success(savedSchemas)

            } catch (t: Throwable) {
                val apiError = ErrorHandler.parseError(t)
                Resource.Error(apiError.getErrorMessage())
            }
        }
    }
}