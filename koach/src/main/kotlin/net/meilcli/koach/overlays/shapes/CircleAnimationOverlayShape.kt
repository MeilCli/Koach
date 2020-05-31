package net.meilcli.koach.overlays.shapes

import android.animation.TimeInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.Px
import net.meilcli.koach.IAnimationOverlayShape
import net.meilcli.koach.TargetViewSpec
import kotlin.math.max

class CircleAnimationOverlayShape(
    @Px private val margin: Int,
    @Px private val animationRadius: Int,
    override val duration: Long,
    override val interpolator: TimeInterpolator,
    override val repeatCount: Int = 0,
    override val repeatMode: Int? = null,
    override val animations: IAnimationOverlayShape.IAnimations = IAnimationOverlayShape.IAnimations.expand
) : IAnimationOverlayShape {

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec) {
        draw(canvas, paint, targetViewSpec, 0f)
    }

    override fun draw(canvas: Canvas, paint: Paint, targetViewSpec: TargetViewSpec, value: Float) {
        val targetRect = targetViewSpec.rect
        val radius = max(
            targetRect.width(),
            targetRect.height()
        ).toFloat() + margin + animationRadius * value
        canvas.drawCircle(targetRect.exactCenterX(), targetRect.exactCenterY(), radius, paint)
    }
}