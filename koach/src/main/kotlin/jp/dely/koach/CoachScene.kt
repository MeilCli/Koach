package jp.dely.koach

class CoachScene(
    val id: String,
    val overlayShape: IOverlayShape,
    val targetViewFinder: IViewFinder,
    val coachSceneUis: List<CoachSceneUi>
) {
    constructor(
        id: String,
        overlayShape: IOverlayShape,
        targetViewFinder: IViewFinder,
        coachViewProvider: IViewProvider,
        coachLayoutProvider: ICoachSceneLayoutProvider
    ) : this(
        id,
        overlayShape,
        targetViewFinder,
        listOf(
            CoachSceneUi(
                coachViewProvider,
                coachLayoutProvider
            )
        )
    )
}