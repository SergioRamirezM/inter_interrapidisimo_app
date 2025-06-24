package com.interrapidisimo.interrapidapp.domain.usecase

import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity
import com.interrapidisimo.interrapidapp.data.mapper.toEntity
import com.interrapidisimo.interrapidapp.data.model.ApiError
import com.interrapidisimo.interrapidapp.data.repository.DatabaseRepository
import com.interrapidisimo.interrapidapp.data.repository.ServiceRepository
import com.interrapidisimo.interrapidapp.util.ErrorHandler
import com.interrapidisimo.interrapidapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SyncLocalitiesUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(): Resource<List<LocalityEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = serviceRepository.getLocalities()

                if (!response.isSuccessful) {
                    val apiError = ApiError(
                        message = "Código HTTP: ${response.code()}",
                        code = response.code(),
                        errorStatus = ApiError.ErrorStatus.UNKNOWN_ERROR
                    )
                    return@withContext Resource.Error(apiError.getErrorMessage())
                }

                val entities = response.body().orEmpty().map { it.toEntity() }
                databaseRepository.saveLocalities(entities)

                val savedLocalities = databaseRepository.loadLocalities()
                Resource.Success(savedLocalities)
            } catch (t: Throwable) {
                val apiError = ErrorHandler.parseError(t)
                Resource.Error(apiError.getErrorMessage())
            }
        }
    }
}