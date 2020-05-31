package net.meilcli.koach.overlays.animates

import net.meilcli.koach.Coach
import net.meilcli.koach.CoachScene
import net.meilcli.koach.IOverlayAnimatedListener

object HideOnEndOverlayAnimatedListener : IOverlayAnimatedListener {

    override fun animationEvent(
        coach: Coach,
        currentScene: CoachScene,
        event: IOverlayAnimatedListener.Event
    ) {
        if (event == IOverlayAnimatedListener.Event.End) {
            coach.hide()
        }
    }
}