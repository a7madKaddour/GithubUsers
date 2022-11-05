package com.ahmadkadddour.githubuser.domain.model

data class UserEntity(
    val id: Long,
    val githubHandle: String,
    val githubUrl: String,
    val dataUrl: String,
    val imageUrl: String?,
    val blogUrl: String?,
    val username: String?,
    val email: String?,
    val bio: String?,
    val address: String?
)
