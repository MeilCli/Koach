package net.meilcli.koach.app

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animation_shape.*
import net.meilcli.koach.Coach
import net.meilcli.koach.CoachOverlay
import net.meilcli.koach.CoachScene
import net.meilcli.koach.layouts.providers.AnchorCoachSceneLayoutProvider
import net.meilcli.koach.overlays.shapes.CircleAnimationOverlayShape
import net.meilcli.koach.overlays.shapes.EllipseAnimationOverlayShape
import net.meilcli.koach.overlays.shapes.PointerAnimationOverlayShape
import net.meilcli.koach.overlays.shapes.RectAnimationOverlayShape
import net.meilcli.koach.views.finders.IdViewFinder
import net.meilcli.koach.views.providers.TextViewProvider

class AnimationShapeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_shape)

        rect.setOnClickListener {
            showRectCoach()
        }
        circle.setOnClickListener {
            showCircleCoach()
        }
        ellipse.setOnClickListener {
            showEllipseCoach()
        }
        pointer.setOnClickListener {
            showPointerCoach()
        }
        showFirstCoach()
    }

    private fun Coach.Creator.createRectScene(): CoachScene {
        return CoachScene(
            "rect",
            RectAnimationOverlayShape(
                margin = 8.dp,
                radius = 16.dp,
                animationSize = 16.dp,
                duration = 1000L,
                interpolator = AccelerateInterpolator(),
                repeatCount = ValueAnimator.INFINITE,
                repeatMode = ValueAnimator.REVERSE
            ),
            IdViewFinder(R.id.rect),
            TextViewProvider(
                text = "Rect",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 48.dp
            )
        )
    }

    private fun Coach.Creator.createCircleScene(): CoachScene {
        return CoachScene(
            "circle",
            CircleAnimationOverlayShape(
                margin = 8.dp,
                animationRadius = 16.dp,
                duration = 1000L,
                interpolator = AccelerateInterpolator(),
                repeatCount = ValueAnimator.INFINITE,
                repeatMode = ValueAnimator.REVERSE
            ),
            IdViewFinder(R.id.circle),
            TextViewProvider(
                text = "Circle",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 48.dp
            )
        )
    }

    private fun Coach.Creator.createEllipseScene(): CoachScene {
        return CoachScene(
            "ellipse",
            EllipseAnimationOverlayShape(
                margin = 8.dp,
                animationRadius = 16.dp,
                duration = 1000L,
                interpolator = AccelerateInterpolator(),
                repeatCount = ValueAnimator.INFINITE,
                repeatMode = ValueAnimator.REVERSE
            ),
            IdViewFinder(R.id.ellipse),
            TextViewProvider(
                text = "Ellipse",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 48.dp
            )
        )
    }

    private fun Coach.Creator.createPointerScene(): CoachScene {
        return CoachScene(
            "pointer",
            PointerAnimationOverlayShape(
                animationRadius = 48.dp,
                duration = 1000L,
                interpolator = AccelerateInterpolator(),
                repeatCount = ValueAnimator.INFINITE,
                repeatMode = ValueAnimator.REVERSE
            ),
            IdViewFinder(R.id.pointer),
            TextViewProvider(
                text = "Pointer",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 48.dp
            )
        )
    }

    private fun showFirstCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createRectScene())
            addScene(createCircleScene())
            addScene(createEllipseScene())
            addScene(createPointerScene())
        }.scheduleShow()
    }

    private fun showRectCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createRectScene())
        }.show()
    }

    private fun showCircleCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createCircleScene())
        }.show()
    }

    private fun showEllipseCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createEllipseScene())
        }.show()
    }

    private fun showPointerCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createPointerScene())
        }.show()
    }
}