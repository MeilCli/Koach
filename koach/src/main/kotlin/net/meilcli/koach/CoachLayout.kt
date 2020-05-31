package net.meilcli.koach

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

@SuppressLint("ViewConstructor")
class CoachLayout(context: Context, coach: Coach) : FrameLayout(context) {

    private val overlayView = CoachOverlayView(context, coach)
    private var currentCoachScene: CoachScene? = null
    private var currentCoachSceneLayout: ICoachSceneLayout? = null

    init {
        addView(overlayView)
    }

    fun replaceScene(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        if (currentCoachScene != null && currentCoachSceneLayout != null) {
            val layout = currentCoachSceneLayout as? ViewGroup
            if (layout != null) {
                removeView(layout)
            }
            currentCoachScene = null
            currentCoachSceneLayout = null
        }

        val coachSceneLayout = coachScene.layoutProvider.provide(context, targetViewSpec)
        if (coachSceneLayout !is View) {
            throw IllegalStateException("CoachSceneLayout must implement View")
        }
        addView(coachSceneLayout)

        val coachView = coachScene.coachViewProvider.provide(context)
        coachSceneLayout.addCoachView(coachView)

        overlayView.setScene(coachScene, targetViewSpec)

        currentCoachScene = coachScene
        currentCoachSceneLayout = coachSceneLayout
    }

    fun finishAnimation() {
        overlayView.finishAnimation()
    }
}