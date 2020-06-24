package jp.dely.koach.scenes.animators

import android.animation.TimeInterpolator
import android.graphics.Paint
import android.view.View
import android.view.animation.AccelerateInterpolator
import jp.dely.koach.*

class EmptyCoachSceneAnimator(
    override val duration: Long
) : ICoachSceneAnimator, IRepeatableCoachSceneAnimator {

    override val interpolator: TimeInterpolator = AccelerateInterpolator()
    override val animation: IAnimation = Animation.none
    override val repeatCount = 0
    override val repeatMode: Int? = null

    override fun updateView(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        view: View,
        value: Float
    ) {
    }

    override fun updateOverlay(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        paint: Paint,
        value: Float
    ) {
    }
}