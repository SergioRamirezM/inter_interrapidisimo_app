package com.interrapidisimo.interrapidapp.domain.usecase

import com.interrapidisimo.interrapidapp.data.repository.ServiceRepository
import retrofit2.HttpException
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(localVersion: Int): VersionStatus {
        val response = serviceRepository.getControlVersion()
        if (!response.isSuccessful) {
            throw HttpException(response)
        }

        val remoteVersion = response.body()
            ?: throw Exception("Versión remota nula")

        return when {
            remoteVersion > localVersion -> VersionStatus.UPDATE_REQUIRED
            remoteVersion < localVersion -> VersionStatus.AHEAD
            else -> VersionStatus.UP_TO_DATE
        }
    }
}