package com.ahmadkadddour.githubuser.data.provider.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmadkadddour.githubuser.data.util.constants.DatabaseConstants

@Database(
    version = DatabaseConstants.DATABASE_VERSION,
    entities = []
)
abstract class AppDatabase : RoomDatabase()