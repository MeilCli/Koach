package jp.dely.koach

import android.graphics.Paint
import android.view.View

interface ICoachSceneAnimator : IAnimationProperty {

    fun updateView(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        view: View,
        value: Float
    )

    fun updateOverlay(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        paint: Paint,
        value: Float
    )
}