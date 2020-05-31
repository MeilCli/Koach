package net.meilcli.koach

class CoachScene(
    val id: String,
    val overlayShape: IOverlayShape,
    val targetViewFinder: IViewFinder,
    val coachViewProvider: IViewProvider,
    val layoutProvider: ICoachSceneLayoutProvider
)