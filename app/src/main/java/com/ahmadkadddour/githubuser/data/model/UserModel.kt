package com.ahmadkadddour.githubuser.data.model

data class UserModel(
    val id: Long,
    val login: String,
    val url: String,
    val htmlUrl: String,
    val username: String?,
    val avatarUrl: String?,
    val blogUrl: String?,
    val bio: String?,
    val address: String?,
    val email: String?
)
