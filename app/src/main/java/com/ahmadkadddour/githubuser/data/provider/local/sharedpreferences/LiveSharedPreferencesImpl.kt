package com.ahmadkadddour.githubuser.data.provider.local.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LiveSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LiveSharedPreferences {

    private val activeFlows = mutableMapOf<String, MutableList<MutableStateFlow<Any?>>>()

    private fun <T> createNewFlow(
        key: String,
        value: T
    ): Flow<T> {
        val createdFlow = MutableStateFlow(value)
        activeFlows.getOrPut(key) { mutableListOf() }.add(createdFlow as MutableStateFlow<Any?>)
        return createdFlow.onCompletion {
            activeFlows[key]?.remove(createdFlow)
        }
    }

    override fun getIntFlow(key: String, defaultValue: Int): Flow<Int> {
        return createNewFlow(key, getInt(key, defaultValue))
    }

    override fun getLongFlow(key: String, defaultValue: Long): Flow<Long> {
        return createNewFlow(key, getLong(key, defaultValue))
    }

    override fun getFloatFlow(key: String, defaultValue: Float): Flow<Float> {
        return createNewFlow(key, getFloat(key, defaultValue))
    }

    override fun getStringFlow(key: String, defaultValue: String?): Flow<String?> {
        return createNewFlow(key, getString(key, defaultValue))
    }

    override fun getBooleanFlow(key: String, defaultValue: Boolean): Flow<Boolean> {
        return createNewFlow(key, getBoolean(key, defaultValue))
    }

    private fun updateValue(
        key: String,
        value: Any?,
        action: SharedPreferences.Editor.() -> Unit
    ){
        sharedPreferences.edit(action = action)
        activeFlows[key]?.forEach { it.value = value }
    }

    override fun putInt(key: String, value: Int) {
        updateValue(key, value){
            putInt(key, value)
        }
    }

    override fun putLong(key: String, value: Long) {
        updateValue(key, value){
            putLong(key, value)
        }
    }

    override fun putFloat(key: String, value: Float) {
        updateValue(key, value){
            putFloat(key, value)
        }
    }

    override fun putString(key: String, value: String?) {
        updateValue(key, value){
            putString(key, value)
        }
    }

    override fun putBoolean(key: String, value: Boolean) {
        updateValue(key, value){
            putBoolean(key, value)
        }
    }

    override fun getInt(key: String, defaultValue: Int): Int =
        sharedPreferences.getInt(key, defaultValue)

    override fun getLong(key: String, defaultValue: Long): Long =
        sharedPreferences.getLong(key, defaultValue)

    override fun getFloat(key: String, defaultValue: Float): Float =
        sharedPreferences.getFloat(key, defaultValue)

    override fun getString(key: String, defaultValue: String?): String? =
        sharedPreferences.getString(key, defaultValue)

    override fun getBoolean(key: String, defaultValue: Boolean) =
        sharedPreferences.getBoolean(key, defaultValue)

    override fun clearAll() {
        sharedPreferences.edit {
            clear()
        }
    }
}