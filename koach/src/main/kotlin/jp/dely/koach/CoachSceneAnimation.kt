package jp.dely.koach

class CoachSceneAnimation(
    val startAnimator: ICoachSceneAnimator? = null,
    val centerAnimator: IRepeatableCoachSceneAnimator? = null,
    val endAnimator: ICoachSceneAnimator? = null,
    val connectionStrategy: IAnimationConnectionStrategy? = null,
    val listener: ICoachSceneAnimatedListener? = null
)