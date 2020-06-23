package jp.dely.koach

interface IViewAnimator : IAnimationProperty {

    fun update(
        coach: Coach,
        coachScene: CoachScene,
        targetViewSpec: ViewSpec,
        value: Float
    )
}