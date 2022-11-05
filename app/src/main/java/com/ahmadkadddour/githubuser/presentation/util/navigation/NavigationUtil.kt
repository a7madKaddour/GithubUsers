package com.ahmadkadddour.githubuser.presentation.util.navigation

import android.content.Intent
import android.net.Uri


object NavigationUtil {
    fun openBrowserIntent(url: String): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}