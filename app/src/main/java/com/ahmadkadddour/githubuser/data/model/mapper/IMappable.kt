package com.ahmadkadddour.githubuser.data.model.mapper

interface IMappable<TO, FROM> {
    fun map(model: FROM): TO
}