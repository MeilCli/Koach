package net.meilcli.koach.overlays.shapes

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import net.meilcli.koach.IOverlayShape
import net.meilcli.koach.TargetViewSpec
import kotlin.math.max

class CircleOverlayShape(
    @Px private val margin: Int
) : IOverlayShape {

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec) {
        val targetRect = targetViewSpec.rect
        val radius = max(targetRect.width(), targetRect.height()).toFloat() + margin
        canvas.drawCircle(targetRect.exactCenterX(), targetRect.exactCenterY(), radius, paint)
    }
}