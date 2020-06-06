package net.meilcli.koach

import android.animation.TimeInterpolator

interface IAnimationProperty {

    val duration: Long

    val interpolator: TimeInterpolator

    val animation: IAnimation
}