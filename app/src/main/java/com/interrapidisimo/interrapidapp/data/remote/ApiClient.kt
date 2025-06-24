package com.interrapidisimo.interrapidapp.data.remote

import com.interrapidisimo.interrapidapp.data.remote.dto.request.LoginRequestDto
import com.interrapidisimo.interrapidapp.data.remote.dto.response.LocalityResponseDto
import com.interrapidisimo.interrapidapp.data.remote.dto.response.LoginResponseDto
import com.interrapidisimo.interrapidapp.data.remote.dto.response.SchemaResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiClient {

    @GET(Endpoints.VERSION_CHECK)
    suspend fun getControlVersion(): Response<Int>

    @Headers(
        "Accept: ${ApiHeaders.ACCEPT}",
        "Content-Type: ${ApiHeaders.CONTENT_TYPE}",
        "Usuario: ${ApiHeaders.USER}",
        "Identificacion: ${ApiHeaders.IDENTIFICATION}",
        "IdUsuario: ${ApiHeaders.ID_USER}",
        "IdCentroServicio: ${ApiHeaders.ID_CENTRO_SERV}",
        "NombreCentroServicio: ${ApiHeaders.NOMBRE_CENTRO_SERV}",
        "IdAplicativoOrigen: ${ApiHeaders.ID_APP_ORIGEN}"
    )
    @POST(Endpoints.LOGIN)
    suspend fun postLogIn(
        @Body request: LoginRequestDto
    ): Response<LoginResponseDto>

    @Headers(
        "Accept: ${ApiHeaders.ACCEPT}",
        "Content-Type: ${ApiHeaders.CONTENT_TYPE}",
        "Usuario: ${ApiHeaders.USER}",
        "Identificacion: ${ApiHeaders.IDENTIFICATION}",
        "IdUsuario: ${ApiHeaders.ID_USER}",
        "IdCentroServicio: ${ApiHeaders.ID_CENTRO_SERV}",
        "NombreCentroServicio: ${ApiHeaders.NOMBRE_CENTRO_SERV}",
        "IdAplicativoOrigen: ${ApiHeaders.ID_APP_ORIGEN}"
    )
    @GET(Endpoints.SCHEMA)
    suspend fun getSchema(): Response<List<SchemaResponseDto>>

    @GET(Endpoints.LOCALITIES)
    suspend fun getLocalities(): Response<List<LocalityResponseDto>>
}