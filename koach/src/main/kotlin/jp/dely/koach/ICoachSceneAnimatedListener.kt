package jp.dely.koach

interface ICoachSceneAnimatedListener : IAnimatedListener {

    enum class Type {
        Start, Center, End
    }

    fun onCurrentAnimator(animator: ICoachSceneAnimator, type: Type)
}