package com.example.pocandroid

class GetObjectUsecase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getObject()
}