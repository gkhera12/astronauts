package com.example.astronauts.datalayer

data class ApiResult<T>(
    val response: T?,
    val error: ErrorType
)

enum class ErrorType {
    None,
    Network
}