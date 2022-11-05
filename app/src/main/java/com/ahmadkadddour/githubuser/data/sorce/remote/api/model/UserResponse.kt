package com.ahmadkadddour.githubuser.data.sorce.remote.api.model

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "login") val login: String,
    @Json(name = "url") val url: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "name") val username: String?,
    @Json(name = "avatar_url") val avatarUrl: String?,
    @Json(name = "blog") val blogUrl: String?,
    @Json(name = "bio") val bio: String?,
    @Json(name = "location") val address: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "twitter_username") val twitterUserName: String?,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "updated_at") val updatedAt: String?
)