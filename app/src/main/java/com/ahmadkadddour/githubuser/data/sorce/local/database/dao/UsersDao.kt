package com.ahmadkadddour.githubuser.data.sorce.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmadkadddour.githubuser.data.sorce.local.database.model.LocalUserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<LocalUserModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: LocalUserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<LocalUserModel>)

    @Query("SELECT * FROM user WHERE user.login = :handle")
    fun getUserByName(handle: String): Flow<LocalUserModel>

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
}