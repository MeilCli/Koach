package jp.dely.koach.overlays.shapes

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import jp.dely.koach.Animation
import jp.dely.koach.IAnimation
import jp.dely.koach.IAnimationOverlayShape
import jp.dely.koach.ViewSpec

class PointerAnimationOverlayShape(
    @Px private val animationRadius: Int,
    override val duration: Long,
    override val interpolator: TimeInterpolator,
    override val repeatCount: Int = 0,
    override val repeatMode: Int? = null,
    override val animation: IAnimation = Animation.contract
) : IAnimationOverlayShape {

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        draw(canvas, paint, targetViewSpec, 1f)
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec, value: Float) {
        val targetRect = targetViewSpec.rect
        val radius = animationRadius * value
        canvas.drawCircle(targetRect.exactCenterX(), targetRect.exactCenterY(), radius, paint)
    }
}