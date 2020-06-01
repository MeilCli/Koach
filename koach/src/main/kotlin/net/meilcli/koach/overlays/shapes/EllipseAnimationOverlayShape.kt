package net.meilcli.koach.overlays.shapes

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import net.meilcli.koach.IAnimationOverlayShape
import net.meilcli.koach.ViewSpec

class EllipseAnimationOverlayShape(
    @Px private val margin: Int,
    @Px private val animationRadius: Int,
    private val direction: Direction = Direction.Horizontal,
    override val duration: Long,
    override val interpolator: TimeInterpolator,
    override val repeatCount: Int = 0,
    override val repeatMode: Int? = null,
    override val animations: IAnimationOverlayShape.IAnimations = IAnimationOverlayShape.IAnimations.expand
) : IAnimationOverlayShape {

    enum class Direction {
        Vertical, Horizontal
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        draw(canvas, paint, targetViewSpec, 0f)
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec, value: Float) {
        when (direction) {
            Direction.Horizontal -> drawHorizontal(canvas, paint, targetViewSpec, value)
            Direction.Vertical -> drawVertical(canvas, paint, targetViewSpec, value)
        }
    }

    private fun drawHorizontal(
        canvas: Canvas,
        paint: Paint,
        targetViewSpec: ViewSpec,
        value: Float
    ) {
        val targetRect = targetViewSpec.rect
        val halfHeight = targetRect.height() / 2f
        val radius = targetRect.height().toFloat() / 2f + margin + animationRadius * value
        val animationHeight = animationRadius * value

        canvas.drawCircle(targetRect.left + halfHeight, targetRect.top + halfHeight, radius, paint)
        canvas.drawCircle(targetRect.right - halfHeight, targetRect.top + halfHeight, radius, paint)
        canvas.drawRect(
            targetRect.left + halfHeight,
            targetRect.top - margin.toFloat() - animationHeight,
            targetRect.right - halfHeight,
            targetRect.bottom + margin.toFloat() + animationHeight,
            paint
        )
    }

    private fun drawVertical(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec, value: Float) {
        val targetRect = targetViewSpec.rect
        val halfWidth = targetRect.width() / 2f
        val radius = targetRect.width().toFloat() / 2f + margin + animationRadius * value
        val animationWidth = animationRadius * value

        canvas.drawCircle(targetRect.left + halfWidth, targetRect.top + halfWidth, radius, paint)
        canvas.drawCircle(targetRect.left + halfWidth, targetRect.bottom - halfWidth, radius, paint)
        canvas.drawRect(
            targetRect.left - margin.toFloat() - animationWidth,
            targetRect.top + halfWidth,
            targetRect.right + margin.toFloat() + animationWidth,
            targetRect.bottom - halfWidth,
            paint
        )
    }
}