package com.example.pocandroid

class Repository(private val apiService: ApiService) {
    suspend fun getObject() = apiService.getObjects()
}