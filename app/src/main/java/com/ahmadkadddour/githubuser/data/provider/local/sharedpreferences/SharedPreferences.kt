package com.ahmadkadddour.githubuser.data.provider.local.sharedpreferences

import kotlinx.coroutines.flow.Flow

interface SharedPreferences {

    fun putInt(key: String, value: Int)
    fun putLong(key: String, value: Long)
    fun putFloat(key: String, value: Float)
    fun putString(key: String, value: String?)
    fun putBoolean(key: String, value: Boolean)

    fun getInt(key: String, defaultValue: Int): Int
    fun getLong(key: String, defaultValue: Long): Long
    fun getFloat(key: String, defaultValue: Float): Float
    fun getString(key: String, defaultValue: String?): String?
    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun clearAll()
}

interface LiveSharedPreferences : SharedPreferences {
    fun getIntFlow(key: String, defaultValue: Int): Flow<Int>
    fun getLongFlow(key: String, defaultValue: Long): Flow<Long>
    fun getFloatFlow(key: String, defaultValue: Float): Flow<Float>
    fun getStringFlow(key: String, defaultValue: String?): Flow<String?>
    fun getBooleanFlow(key: String, defaultValue: Boolean): Flow<Boolean>
}