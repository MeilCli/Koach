package net.meilcli.koach

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint

interface IAnimationOverlayShape : IOverlayShape {

    enum class Type {
        Expand, Contract
    }

    val duration: Long

    val interpolator: TimeInterpolator

    /**
     * count value or [android.animation.ValueAnimator.INFINITE]
     */
    val repeatCount: Int

    /**
     * [android.animation.ValueAnimator]'s constant value
     */
    val repeatMode: Int?

    val type: Type

    /**
     * [value] will be range of [0, 1]
     */
    fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec, value: Float)
}