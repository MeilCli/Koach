package jp.dely.koach

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout

@SuppressLint("ViewConstructor")
class CoachLayout(context: Context, coach: Coach) : FrameLayout(context) {

    private val overlayView = CoachOverlayView(context, coach)
    private var currentCoachScene: CoachScene? = null
    private var currentCoachSceneLayouts = mutableListOf<ICoachSceneLayout>()

    init {
        addView(overlayView)
    }

    fun replaceScene(coachScene: CoachScene, targetViewSpec: ViewSpec) {
        if (currentCoachScene != null) {
            for (layout in currentCoachSceneLayouts) {
                if (layout is View) {
                    removeView(layout)
                }
            }
            currentCoachScene = null
            currentCoachSceneLayouts.clear()
        }

        for (coachSceneUi in coachScene.coachSceneUis) {
            val coachSceneLayout = coachSceneUi.layoutProvider.provide(context, targetViewSpec)
            if (coachSceneLayout !is View) {
                throw IllegalStateException("CoachSceneLayout must implement View")
            }
            addView(coachSceneLayout)

            val coachView = coachSceneUi.coachViewProvider.provide(context, coachSceneLayout)
            coachSceneLayout.addCoachView(coachView)
            currentCoachSceneLayouts.add(coachSceneLayout)
        }

        overlayView.setScene(coachScene, targetViewSpec)

        currentCoachScene = coachScene
    }

    fun finishAnimation() {
        overlayView.finishAnimation()
    }
}