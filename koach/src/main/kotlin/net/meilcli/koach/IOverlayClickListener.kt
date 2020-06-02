package net.meilcli.koach

interface IOverlayClickListener {

    enum class Clicked {
        OutSide, Target
    }

    fun click(coach: Coach, currentScene: CoachScene, clicked: Clicked)
}