package com.ahmadkadddour.githubuser.data.provider.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmadkadddour.githubuser.data.sorce.local.database.dao.UsersDao
import com.ahmadkadddour.githubuser.data.sorce.local.database.model.LocalUserModel
import com.ahmadkadddour.githubuser.data.util.constants.DatabaseConstants

@Database(
    version = DatabaseConstants.DATABASE_VERSION,
    entities = [LocalUserModel::class]
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getUsersDao(): UsersDao
}