package jp.dely.koach.overlays.shapes

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import jp.dely.koach.IOverlayShape
import jp.dely.koach.ViewSpec
import kotlin.math.max

class CircleOverlayShape(
    @Px private val margin: Int
) : IOverlayShape {

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: ViewSpec) {
        val targetRect = targetViewSpec.rect
        val radius = max(targetRect.width(), targetRect.height()) / 2f + margin
        canvas.drawCircle(targetRect.exactCenterX(), targetRect.exactCenterY(), radius, paint)
    }
}