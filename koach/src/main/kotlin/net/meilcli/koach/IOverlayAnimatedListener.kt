package net.meilcli.koach

interface IOverlayAnimatedListener {

    enum class Event {
        Start, End, Cancel, Repeat
    }

    fun animationEvent(coach: Coach, currentScene: CoachScene, event: Event)
}