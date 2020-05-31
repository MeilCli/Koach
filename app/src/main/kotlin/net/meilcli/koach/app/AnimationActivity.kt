package net.meilcli.koach.app

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animation.*
import net.meilcli.koach.Coach
import net.meilcli.koach.CoachOverlay
import net.meilcli.koach.CoachScene
import net.meilcli.koach.layouts.providers.FrameCoachSceneLayoutProvider
import net.meilcli.koach.overlays.animates.NextSceneOnEndOverlayAnimatedListener
import net.meilcli.koach.overlays.clicks.EmptyOverlayClickListener
import net.meilcli.koach.overlays.shapes.PointerAnimationOverlayShape
import net.meilcli.koach.views.finders.IdViewFinder
import net.meilcli.koach.views.providers.TextViewProvider

class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        show.setOnClickListener {
            showCoach(false)
        }

        showCoach(true)
    }

    private fun showCoach(schedule: Boolean) {
        val coach = Coach.create(
            this,
            CoachOverlay(
                clickListener = EmptyOverlayClickListener,
                animatedListener = NextSceneOnEndOverlayAnimatedListener
            )
        ) {
            addScene(
                CoachScene(
                    "text 1",
                    PointerAnimationOverlayShape(
                        animationRadius = 32.dp,
                        duration = 1000L,
                        interpolator = AccelerateInterpolator()
                    ),
                    IdViewFinder(R.id.text1),
                    TextViewProvider(
                        text = "Text 1",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    FrameCoachSceneLayoutProvider(
                        gravity = Gravity.CENTER
                    )
                )
            )
            addScene(
                CoachScene(
                    "text 2",
                    PointerAnimationOverlayShape(
                        animationRadius = 32.dp,
                        duration = 1000L,
                        interpolator = AccelerateInterpolator()
                    ),
                    IdViewFinder(R.id.text2),
                    TextViewProvider(
                        text = "Text 2",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    FrameCoachSceneLayoutProvider(
                        gravity = Gravity.CENTER
                    )
                )
            )
            addScene(
                CoachScene(
                    "text 3",
                    PointerAnimationOverlayShape(
                        animationRadius = 32.dp,
                        duration = 1000L,
                        interpolator = AccelerateInterpolator()
                    ),
                    IdViewFinder(R.id.text3),
                    TextViewProvider(
                        text = "Text 3",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    FrameCoachSceneLayoutProvider(
                        gravity = Gravity.CENTER
                    )
                )
            )
        }

        if (schedule) {
            coach.scheduleShow()
        } else {
            coach.show()
        }
    }
}