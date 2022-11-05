package com.ahmadkadddour.githubuser.data.model.mapper

import com.ahmadkadddour.githubuser.data.model.UserModel
import com.ahmadkadddour.githubuser.data.sorce.remote.api.model.UserResponse
import javax.inject.Inject

class UserResponseMapper @Inject constructor(): IMappable<UserModel, UserResponse> {
    override fun map(model: UserResponse): UserModel {
        return UserModel(
            id = model.id,
            login = model.login,
            url = model.url,
            htmlUrl = model.htmlUrl,
            username = model.username,
            avatarUrl = model.avatarUrl,
            blogUrl = model.blogUrl,
            bio = model.bio,
            address = model.address,
            email = model.email
        )
    }
}