package com.example.astronauts.datalayer.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceLaunchService {
    @GET("astronaut")
    fun getAstronauts(): Call<AstronautsDto>
    @GET("astronaut/{id}")
    fun getAstronautDetail(@Path("id") id: Int): Call<AstronautDto>
}