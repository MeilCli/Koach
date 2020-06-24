package jp.dely.koach.scenes.animators

import android.animation.TimeInterpolator
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import jp.dely.koach.*

class FadeCoachSceneAnimator(
    override val duration: Long,
    override val interpolator: TimeInterpolator,
    override val animation: IAnimation,
    private val mode: Mode = Mode.Both
) : ICoachSceneAnimator {

    enum class Mode {
        ViewOnly, OverlayOnly, Both
    }

    override fun updateView(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        view: View,
        value: Float
    ) {
        view.alpha = valueToAlpha(value)
    }

    override fun updateOverlay(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        paint: Paint,
        value: Float
    ) {
        val defaultColor = coachScene.coachOverlay?.color ?: coach.overlay.color
        val defaultAlpha = Color.alpha(defaultColor)
        paint.alpha = (valueToAlpha(value) * defaultAlpha).toInt()
    }

    private fun valueToAlpha(value: Float): Float {
        return when {
            value <= 0 -> 0f
            1 <= value -> 1f
            else -> value
        }
    }
}