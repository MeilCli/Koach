package jp.dely.koach

interface IRepeatableAnimationProperty : IAnimationProperty {

    /**
     * count value or [android.animation.ValueAnimator.INFINITE]
     */
    val repeatCount: Int

    /**
     * [android.animation.ValueAnimator]'s constant value
     */
    val repeatMode: Int?
}