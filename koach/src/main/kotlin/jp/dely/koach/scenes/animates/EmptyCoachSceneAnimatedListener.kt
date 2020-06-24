package jp.dely.koach.scenes.animates

import jp.dely.koach.*

object EmptyCoachSceneAnimatedListener : ICoachSceneAnimatedListener {

    override fun onCurrentAnimator(
        animator: ICoachSceneAnimator,
        type: ICoachSceneAnimatedListener.Type
    ) {
    }

    override fun animationEvent(coach: Coach, currentScene: CoachScene, event: AnimatedEvent) {
    }
}