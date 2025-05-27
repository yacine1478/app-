package com.example.myapplication.presentation.Data

data class Worker(
    val id: Int,
    val username: String,
    val email: String,
    val passwordHash: String,
    val createdAt: String
)

data class WorkerUpdateRequest(
    val username: String?,
    val email: String?,
    val newPassword: String?
)