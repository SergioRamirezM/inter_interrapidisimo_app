package com.interrapidisimo.interrapidapp.domain.usecase

import com.interrapidisimo.interrapidapp.data.database.entities.UserEntity
import com.interrapidisimo.interrapidapp.data.model.ApiError
import com.interrapidisimo.interrapidapp.data.remote.dto.request.LoginRequestDto
import com.interrapidisimo.interrapidapp.data.repository.DatabaseRepository
import com.interrapidisimo.interrapidapp.data.repository.ServiceRepository
import com.interrapidisimo.interrapidapp.util.ErrorHandler
import com.interrapidisimo.interrapidapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(request: LoginRequestDto): Resource<UserEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val response = serviceRepository.postLogIn(request)

                if (!response.isSuccessful) {
                    val apiError = ApiError(
                        message = "Código HTTP: ${response.code()}",
                        code = response.code(),
                        errorStatus = ApiError.ErrorStatus.UNKNOWN_ERROR
                    )
                    return@withContext Resource.Error(apiError.getErrorMessage())
                }

                response.body()?.let { body ->
                    val user = UserEntity(
                        username = body.username,
                        idNumber = body.idNumber,
                        name = body.name
                    )
                    databaseRepository.saveUser(user)
                    Resource.Success(user)

                } ?: Resource.Error("Respuesta vacía del servidor")
            } catch (t: Throwable) {
                val apiError = ErrorHandler.parseError(t)
                Resource.Error(apiError.getErrorMessage())
            }
        }
    }
}