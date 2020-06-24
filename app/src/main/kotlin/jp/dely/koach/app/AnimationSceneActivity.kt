package jp.dely.koach.app

import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import jp.dely.koach.*
import jp.dely.koach.layouts.RelativeDirection
import jp.dely.koach.layouts.providers.RelativeCoachSceneLayoutProvider
import jp.dely.koach.overlays.clicks.EmptyOverlayClickListener
import jp.dely.koach.overlays.shapes.RectOverlayShape
import jp.dely.koach.scenes.animates.NextSceneOnEndCoachSceneAnimatedListener
import jp.dely.koach.scenes.animators.EmptyCoachSceneAnimator
import jp.dely.koach.scenes.animators.FadeCoachSceneAnimator
import jp.dely.koach.views.finders.IdViewFinder
import jp.dely.koach.views.providers.InflateViewProvider
import kotlinx.android.synthetic.main.activity_animation_scene.*

class AnimationSceneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_scene)

        startOnly.setOnClickListener {
            showStartOnlyAnimationCoach()
        }
        all.setOnClickListener {
            showAllAnimationCoach()
        }

        showFirstCoach()
    }

    private fun Coach.Creator.createStartOnlyAnimationScene(): CoachScene {
        return CoachScene(
            id = "startOnly",
            overlayShape = RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            targetViewFinder = IdViewFinder(R.id.startOnly),
            coachViewProvider = InflateViewProvider(R.layout.coach_sample_horizontal),
            coachLayoutProvider = RelativeCoachSceneLayoutProvider(
                RelativeDirection.Below,
                margin = 24.dp
            ),
            coachSceneAnimation = CoachSceneAnimation(
                startAnimator = FadeCoachSceneAnimator(
                    duration = 500,
                    interpolator = AccelerateInterpolator(),
                    animation = IAnimation.fadeIn
                )
            )
        )
    }

    private fun Coach.Creator.createAllAnimationScene(): CoachScene {
        return CoachScene(
            id = "all",
            overlayShape = RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            targetViewFinder = IdViewFinder(R.id.all),
            coachViewProvider = InflateViewProvider(R.layout.coach_sample_horizontal),
            coachLayoutProvider = RelativeCoachSceneLayoutProvider(
                RelativeDirection.Below,
                margin = 24.dp
            ),
            coachOverlay = CoachOverlay(
                clickListener = EmptyOverlayClickListener,
                canClickSurfaceView = true
            ),
            coachSceneAnimation = CoachSceneAnimation(
                startAnimator = FadeCoachSceneAnimator(
                    duration = 500,
                    interpolator = AccelerateInterpolator(),
                    animation = IAnimation.fadeIn
                ),
                centerAnimator = EmptyCoachSceneAnimator(
                    duration = 2000
                ),
                endAnimator = FadeCoachSceneAnimator(
                    duration = 500,
                    interpolator = AccelerateInterpolator(),
                    animation = IAnimation.fadeOut
                ),
                listener = NextSceneOnEndCoachSceneAnimatedListener
            )
        )
    }

    private fun showFirstCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createStartOnlyAnimationScene())
            addScene(createAllAnimationScene())
        }.scheduleShow()
    }

    private fun showStartOnlyAnimationCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createStartOnlyAnimationScene())
        }.show()
    }

    private fun showAllAnimationCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createAllAnimationScene())
        }.show()
    }
}