package net.meilcli.koach.overlays.shapes

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import net.meilcli.koach.IAnimation
import net.meilcli.koach.IAnimationOverlayShape
import net.meilcli.koach.ViewSpec

class EllipseAnimationOverlayShape(
    @Px private val marginTop: Int,
    @Px private val marginBottom: Int,
    @Px private val marginLeft: Int,
    @Px private val marginRight: Int,
    @Px private val animationRadius: Int,
    private val direction: Direction = Direction.Horizontal,
    override val duration: Long,
    override val interpolator: TimeInterpolator,
    override val repeatCount: Int = 0,
    override val repeatMode: Int? = null,
    override val animation: IAnimation = IAnimation.expand
) : IAnimationOverlayShape {

    enum class Direction {
        Vertical, Horizontal
    }

    constructor(
        @Px margin: Int,
        @Px animationRadius: Int,
        direction: Direction = Direction.Horizontal,
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
        animationRadius,
        direction,
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
        val radius =
            targetRect.height() / 2f + (marginTop + marginBottom) / 2f + animationRadius * value
        val animationHeight = animationRadius * value

        canvas.drawCircle(
            targetRect.left + halfHeight - marginLeft,
            targetRect.top + halfHeight,
            radius,
            paint
        )
        canvas.drawCircle(
            targetRect.right - halfHeight + marginRight,
            targetRect.top + halfHeight,
            radius,
            paint
        )
        canvas.drawRect(
            targetRect.left + halfHeight - marginLeft,
            targetRect.top - marginTop.toFloat() - animationHeight,
            targetRect.right - halfHeight + marginRight,
            targetRect.bottom + marginBottom.toFloat() + animationHeight,
            paint
        )
    }

    private fun drawVertical(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec, value: Float) {
        val targetRect = targetViewSpec.rect
        val halfWidth = targetRect.width() / 2f
        val radius =
            targetRect.width() / 2f + (marginLeft + marginRight) / 2f + animationRadius * value
        val animationWidth = animationRadius * value

        canvas.drawCircle(
            targetRect.left + halfWidth,
            targetRect.top + halfWidth - marginTop,
            radius,
            paint
        )
        canvas.drawCircle(
            targetRect.left + halfWidth,
            targetRect.bottom - halfWidth + marginBottom,
            radius,
            paint
        )
        canvas.drawRect(
            targetRect.left - marginLeft.toFloat() - animationWidth,
            targetRect.top + halfWidth - marginTop,
            targetRect.right + marginRight.toFloat() + animationWidth,
            targetRect.bottom - halfWidth + marginBottom,
            paint
        )
    }
}