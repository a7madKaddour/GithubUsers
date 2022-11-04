package com.ahmadkadddour.githubuser.data.util.network.networkchecker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import javax.inject.Inject

class NetworkCheckerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkChecker {
    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override suspend fun isConnected(): Boolean =
        thereIsActiveNetwork() && isInternetAvailable()

    private suspend fun isInternetAvailable() = withContext(Dispatchers.IO) {
        try {
            InetAddress.getByName("www.google.com")
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun thereIsActiveNetwork(): Boolean {
        return try {
            connectivityManager.run {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val networkCapabilities = getNetworkCapabilities(activeNetwork)
                    return networkCapabilities?.isConnected() ?: false
                } else {
                    allNetworks.toList()
                        .map { network -> getNetworkCapabilities(network) }
                        .any { it?.isConnected() ?: false }
                }
            }
        } catch (e: Exception) {
            false
        }
    }
}

fun NetworkCapabilities.isConnected(): Boolean {
    return listOf(
        hasTransport(NetworkCapabilities.TRANSPORT_WIFI),
        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR),
        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET),
        hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH),
        hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    ).any { it }
}