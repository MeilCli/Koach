package jp.dely.koach.overlays.animates

import jp.dely.koach.Coach
import jp.dely.koach.CoachScene
import jp.dely.koach.IOverlayAnimatedListener

object NextSceneOnRepeatOverlayAnimatedListener :
    IOverlayAnimatedListener {

    override fun animationEvent(
        coach: Coach,
        currentScene: CoachScene,
        event: IOverlayAnimatedListener.Event
    ) {
        if (event == IOverlayAnimatedListener.Event.Repeat) {
            coach.showNextSceneOrHide()
        }
    }
}