package net.meilcli.koach.overlays.shapes

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import net.meilcli.koach.IOverlayShape
import net.meilcli.koach.ViewSpec

class EllipseOverlayShape(
    @Px private val marginTop: Int,
    @Px private val marginBottom: Int,
    @Px private val marginLeft: Int,
    @Px private val marginRight: Int,
    private val direction: Direction = Direction.Horizontal
) : IOverlayShape {

    enum class Direction {
        Vertical, Horizontal
    }

    constructor(
        @Px margin: Int,
        direction: Direction = Direction.Horizontal
    ) : this(margin, margin, margin, margin, direction)

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        when (direction) {
            Direction.Horizontal -> drawHorizontal(canvas, paint, targetViewSpec)
            Direction.Vertical -> drawVertical(canvas, paint, targetViewSpec)
        }
    }

    private fun drawHorizontal(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        val targetRect = targetViewSpec.rect
        val halfHeight = targetRect.height() / 2f
        val radius = targetRect.height() / 2f + (marginTop + marginBottom) / 2f

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
            targetRect.top - marginTop.toFloat(),
            targetRect.right - halfHeight + marginRight,
            targetRect.bottom + marginBottom.toFloat(),
            paint
        )
    }

    private fun drawVertical(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        val targetRect = targetViewSpec.rect
        val halfWidth = targetRect.width() / 2f
        val radius = targetRect.width() / 2f + (marginLeft + marginRight) / 2f

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
            targetRect.left - marginLeft.toFloat(),
            targetRect.top + halfWidth - marginTop,
            targetRect.right + marginTop.toFloat(),
            targetRect.bottom - halfWidth + marginBottom,
            paint
        )
    }
}