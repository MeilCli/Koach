package jp.dely.koach.app

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_shape.*
import jp.dely.koach.Coach
import jp.dely.koach.CoachOverlay
import jp.dely.koach.CoachScene
import jp.dely.koach.layouts.providers.AnchorCoachSceneLayoutProvider
import jp.dely.koach.overlays.shapes.CircleOverlayShape
import jp.dely.koach.overlays.shapes.EllipseOverlayShape
import jp.dely.koach.overlays.shapes.RectOverlayShape
import jp.dely.koach.views.finders.IdViewFinder
import jp.dely.koach.views.providers.TextViewProvider

class ShapeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape)

        rect.setOnClickListener {
            showRectCoach()
        }
        circle.setOnClickListener {
            showCircleCoach()
        }
        ellipse.setOnClickListener {
            showEllipseCoach()
        }
        showFirstCoach()
    }

    private fun Coach.Creator.createRectScene(): CoachScene {
        return CoachScene(
            "rect",
            RectOverlayShape(
                margin = 8.dp,
                radius = 16.dp
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
                marginVertical = 32.dp
            )
        )
    }

    private fun Coach.Creator.createCircleScene(): CoachScene {
        return CoachScene(
            "circle",
            CircleOverlayShape(margin = 8.dp),
            IdViewFinder(R.id.circle),
            TextViewProvider(
                text = "Circle",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 32.dp
            )
        )
    }

    private fun Coach.Creator.createEllipseScene(): CoachScene {
        return CoachScene(
            "ellipse",
            EllipseOverlayShape(margin = 8.dp),
            IdViewFinder(R.id.ellipse),
            TextViewProvider(
                text = "Ellipse",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 32.dp
            )
        )
    }

    private fun showFirstCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createRectScene())
            addScene(createCircleScene())
            addScene(createEllipseScene())
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
}