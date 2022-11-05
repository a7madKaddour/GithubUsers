package com.ahmadkadddour.githubuser.data.model.mapper

import com.ahmadkadddour.githubuser.data.model.UserModel
import com.ahmadkadddour.githubuser.data.sorce.local.database.model.LocalUserModel
import javax.inject.Inject

class LocalUserMapper @Inject constructor() : IMappable<UserModel, LocalUserModel> {
    override fun map(model: LocalUserModel): UserModel {
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

    override fun unmap(model: UserModel): LocalUserModel {
        return LocalUserModel(
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