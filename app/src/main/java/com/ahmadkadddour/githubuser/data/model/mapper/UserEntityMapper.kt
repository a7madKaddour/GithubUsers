package com.ahmadkadddour.githubuser.data.model.mapper

import com.ahmadkadddour.githubuser.data.model.UserModel
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : IMappable<UserEntity, UserModel> {
    override fun map(model: UserModel): UserEntity {
        return UserEntity(
            id = model.id,
            githubHandle = model.login,
            githubUrl = model.htmlUrl,
            dataUrl = model.url,
            imageUrl = model.avatarUrl,
            blogUrl = model.blogUrl,
            username = model.username,
            email = model.email,
            bio = model.bio,
            address = model.address
        )
    }
}