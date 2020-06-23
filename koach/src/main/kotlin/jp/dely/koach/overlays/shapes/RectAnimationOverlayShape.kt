package jp.dely.koach.overlays.shapes

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.annotation.Px
import jp.dely.koach.IAnimation
import jp.dely.koach.IAnimationOverlayShape
import jp.dely.koach.ViewSpec

class RectAnimationOverlayShape(
    @Px private val marginTop: Int,
    @Px private val marginBottom: Int,
    @Px private val marginLeft: Int,
    @Px private val marginRight: Int,
    @Px private val radius: Int,
    @Px private val animationSize: Int,
    override val duration: Long,
    override val interpolator: TimeInterpolator,
    override val repeatCount: Int = 0,
    override val repeatMode: Int? = null,
    override val animation: IAnimation = IAnimation.expand
) : IAnimationOverlayShape {

    constructor(
        @Px margin: Int,
        @Px radius: Int,
        @Px animationSize: Int,
        duration: Long,
        interpolator: TimeInterpolator,
        repeatCount: Int = 0,
        repeatMode: Int? = null,
        animation: IAnimation = IAnimation.expand
    ) : this(
        margin,
        margin,
        margin,
        margin,
        radius,
        animationSize,
        duration,
        interpolator,
        repeatCount,
        repeatMode,
        animation
    )

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        draw(canvas, paint, targetViewSpec, 0f)
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec, value: Float) {
        val targetRect = targetViewSpec.rect
        val rect = RectF(
            targetRect.left.toFloat() - marginLeft - animationSize * value,
            targetRect.top.toFloat() - marginTop - animationSize * value,
            targetRect.right.toFloat() + marginRight + animationSize * value,
            targetRect.bottom.toFloat() + marginBottom + animationSize * value
        )
        canvas.drawRoundRect(rect, radius.toFloat(), radius.toFloat(), paint)
    }
}