package net.meilcli.koach.overlays.shapes

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.annotation.Px
import net.meilcli.koach.IAnimationOverlayShape
import net.meilcli.koach.TargetViewSpec

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
    override val type: IAnimationOverlayShape.Type = IAnimationOverlayShape.Type.Expand
) : IAnimationOverlayShape {

    constructor(
        @Px margin: Int,
        @Px radius: Int,
        @Px animationSize: Int,
        duration: Long,
        interpolator: TimeInterpolator,
        repeatCount: Int = 0,
        repeatMode: Int? = null
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
        repeatMode
    )

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec) {
        draw(canvas, paint, targetViewSpec, 0f)
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec, value: Float) {
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