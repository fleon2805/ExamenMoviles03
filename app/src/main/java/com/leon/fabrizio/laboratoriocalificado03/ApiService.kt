package com.leon.fabrizio.laboratoriocalificado03

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    fun getProfesores(): Call<ApiResponse>
}


