package net.meilcli.koach.overlays.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.annotation.Px
import net.meilcli.koach.IOverlayShape
import net.meilcli.koach.TargetViewSpec

class RectOverlayShape(
    @Px private val marginTop: Int,
    @Px private val marginBottom: Int,
    @Px private val marginLeft: Int,
    @Px private val marginRight: Int,
    @Px private val radius: Int
) : IOverlayShape {

    constructor(
        @Px margin: Int,
        @Px radius: Int
    ) : this(margin, margin, margin, margin, radius)

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec) {
        val targetRect = targetViewSpec.rect
        val rect = RectF(
            targetRect.left.toFloat() - marginLeft,
            targetRect.top.toFloat() - marginTop,
            targetRect.right.toFloat() + marginRight,
            targetRect.bottom.toFloat() + marginBottom
        )
        canvas.drawRoundRect(rect, radius.toFloat(), radius.toFloat(), paint)
    }
}