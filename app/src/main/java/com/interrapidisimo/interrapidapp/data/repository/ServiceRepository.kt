package com.interrapidisimo.interrapidapp.data.repository

import com.interrapidisimo.interrapidapp.data.remote.ApiClient
import com.interrapidisimo.interrapidapp.data.remote.dto.request.LoginRequestDto
import com.interrapidisimo.interrapidapp.data.remote.dto.response.LocalityResponseDto
import com.interrapidisimo.interrapidapp.data.remote.dto.response.LoginResponseDto
import com.interrapidisimo.interrapidapp.data.remote.dto.response.SchemaResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepository @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getControlVersion(): Response<Int> {
        return withContext(Dispatchers.IO) {
            api.getControlVersion()
        }
    }

    suspend fun postLogIn(request: LoginRequestDto): Response<LoginResponseDto> {
        return withContext(Dispatchers.IO) {
            api.postLogIn(request)
        }
    }

    suspend fun getSchemas(): Response<List<SchemaResponseDto>> {
        return withContext(Dispatchers.IO) {
            api.getSchema()
        }
    }

    suspend fun getLocalities(): Response<List<LocalityResponseDto>> {
        return withContext(Dispatchers.IO) {
            api.getLocalities()
        }
    }
}