package jp.dely.koach

interface IAnimatedListener {

    fun animationEvent(coach: Coach, currentScene: CoachScene, event: AnimatedEvent)
}