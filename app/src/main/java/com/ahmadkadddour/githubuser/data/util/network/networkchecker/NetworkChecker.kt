package com.ahmadkadddour.githubuser.data.util.network.networkchecker

interface NetworkChecker {
    suspend fun isConnected(): Boolean
}