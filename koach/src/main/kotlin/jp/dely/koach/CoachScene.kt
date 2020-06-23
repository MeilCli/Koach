package jp.dely.koach

/**
 * [coachOverlay] will override [Coach.overlay]
 */
class CoachScene(
    val id: String,
    val overlayShape: IOverlayShape,
    val targetViewFinder: IViewFinder,
    val coachSceneUis: List<CoachSceneUi>,
    val coachOverlay: CoachOverlay? = null
) {
    constructor(
        id: String,
        overlayShape: IOverlayShape,
        targetViewFinder: IViewFinder,
        coachViewProvider: IViewProvider,
        coachLayoutProvider: ICoachSceneLayoutProvider,
        coachOverlay: CoachOverlay? = null
    ) : this(
        id,
        overlayShape,
        targetViewFinder,
        listOf(
            CoachSceneUi(
                coachViewProvider,
                coachLayoutProvider
            )
        ),
        coachOverlay
    )
}