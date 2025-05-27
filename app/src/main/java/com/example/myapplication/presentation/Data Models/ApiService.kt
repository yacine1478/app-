package com.example.myapplication.presentation.Data

import com.example.myapplication.presentation.view.pages.SolarPanel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SolarApiService {

    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest): Response<AuthResponse>


    @POST("panels")
    suspend fun addPanel(@Body panel: SolarPanel): Response<Unit>

    @GET("panels/same-location")
    suspend fun getPanelsByLocation(@Query("location") location: String): Response<List<SolarPanel>>
}

data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val username: String, val email: String, val password: String)
data class AuthResponse(val token: String, val workerId: Int)