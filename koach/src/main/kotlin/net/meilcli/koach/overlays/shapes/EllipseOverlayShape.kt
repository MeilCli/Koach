package net.meilcli.koach.overlays.shapes

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import net.meilcli.koach.IOverlayShape
import net.meilcli.koach.ViewSpec

class EllipseOverlayShape(
    @Px private val margin: Int,
    private val direction: Direction = Direction.Horizontal
) : IOverlayShape {

    enum class Direction {
        Vertical, Horizontal
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        when (direction) {
            Direction.Horizontal -> drawHorizontal(canvas, paint, targetViewSpec)
            Direction.Vertical -> drawVertical(canvas, paint, targetViewSpec)
        }
    }

    private fun drawHorizontal(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        val targetRect = targetViewSpec.rect
        val halfHeight = targetRect.height() / 2f
        val radius = targetRect.height().toFloat() / 2f + margin

        canvas.drawCircle(targetRect.left + halfHeight, targetRect.top + halfHeight, radius, paint)
        canvas.drawCircle(targetRect.right - halfHeight, targetRect.top + halfHeight, radius, paint)
        canvas.drawRect(
            targetRect.left + halfHeight,
            targetRect.top - margin.toFloat(),
            targetRect.right - halfHeight,
            targetRect.bottom + margin.toFloat(),
            paint
        )
    }

    private fun drawVertical(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        val targetRect = targetViewSpec.rect
        val halfWidth = targetRect.width() / 2f
        val radius = targetRect.width().toFloat() / 2f + margin

        canvas.drawCircle(targetRect.left + halfWidth, targetRect.top + halfWidth, radius, paint)
        canvas.drawCircle(targetRect.left + halfWidth, targetRect.bottom - halfWidth, radius, paint)
        canvas.drawRect(
            targetRect.left - margin.toFloat(),
            targetRect.top + halfWidth,
            targetRect.right + margin.toFloat(),
            targetRect.bottom - halfWidth,
            paint
        )
    }
}