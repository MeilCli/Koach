package net.meilcli.koach.overlays.clicks

import net.meilcli.koach.Coach
import net.meilcli.koach.CoachScene
import net.meilcli.koach.IOverlayClickListener

object NextSceneOverlayClickListener : IOverlayClickListener {

    override fun click(
        coach: Coach,
        currentScene: CoachScene,
        clicked: IOverlayClickListener.Clicked
    ) {
        coach.showNextSceneOrHide()
    }
}