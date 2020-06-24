package jp.dely.koach.scenes.animates

import jp.dely.koach.*

object HideOnEndCoachSceneAnimatedListener : ICoachSceneAnimatedListener {

    private var type: ICoachSceneAnimatedListener.Type? = null

    override fun onCurrentAnimator(
        animator: ICoachSceneAnimator,
        type: ICoachSceneAnimatedListener.Type
    ) {
        this.type = type
    }

    override fun animationEvent(coach: Coach, currentScene: CoachScene, event: AnimatedEvent) {
        if (type == ICoachSceneAnimatedListener.Type.End && event == AnimatedEvent.End) {
            coach.hide()
        }
    }
}