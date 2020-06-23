package jp.dely.koach

import android.animation.TimeInterpolator
import jp.dely.koach.IAnimation

interface IAnimationProperty {

    val duration: Long

    val interpolator: TimeInterpolator

    val animation: IAnimation
}