package com.example.pocandroid

import retrofit2.http.GET

interface ApiService {

    @GET("objects")
    suspend fun getObjects(): List<Product>

}