package jp.dely.koach.overlays.clicks

import jp.dely.koach.Coach
import jp.dely.koach.CoachScene
import jp.dely.koach.IOverlayClickListener

object NextSceneOverlayClickListener : IOverlayClickListener {

    override fun click(
        coach: Coach,
        currentScene: CoachScene,
        clicked: IOverlayClickListener.Clicked
    ) {
        coach.showNextSceneOrHide()
    }
}