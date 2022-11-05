package com.ahmadkadddour.githubuser.data.model.mapper

interface IMappable<TO, FROM> {
    fun map(model: FROM): TO

    fun map(models: List<FROM>): List<TO>{
        return models.map (this::map)
    }
}