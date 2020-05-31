package net.meilcli.koach

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint

interface IAnimationOverlayShape : IOverlayShape {

    interface IAnimations {

        companion object {

            val expand = object : IAnimations {
                override val values = floatArrayOf(0f, 1f)
            }

            val contract = object : IAnimations {
                override val values = floatArrayOf(1f, 0f)
            }
        }

        /**
         * values will be range of [0f, 1f]
         */
        val values: FloatArray
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

    val animations: IAnimations

    /**
     * [value] will be range of [0, 1]
     */
    fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec, value: Float)
}