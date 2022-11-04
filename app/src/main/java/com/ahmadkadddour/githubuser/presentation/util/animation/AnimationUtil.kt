package com.ahmadkadddour.githubuser.presentation.util.animation

import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation

object AnimationUtil {
    fun scaleAnimation(): ScaleAnimation {
        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )

        return scaleAnimation.apply {
            duration = 500
            interpolator = BounceInterpolator()
        }
    }
}