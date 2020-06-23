package jp.dely.koach.app

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coach_view.*
import jp.dely.koach.Coach
import jp.dely.koach.CoachOverlay
import jp.dely.koach.CoachScene
import jp.dely.koach.layouts.providers.AnchorCoachSceneLayoutProvider
import jp.dely.koach.layouts.providers.FrameCoachSceneLayoutProvider
import jp.dely.koach.overlays.shapes.RectOverlayShape
import jp.dely.koach.views.finders.IdViewFinder
import jp.dely.koach.views.providers.InflateViewProvider

class CoachViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach_view)

        anchor.setOnClickListener {
            showAnchorCoach()
        }
        frame.setOnClickListener {
            showFrameCoach()
        }
        showFirstCoach()
    }

    private fun Coach.Creator.createAnchorScene(): CoachScene {
        return CoachScene(
            "anchor",
            RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            IdViewFinder(R.id.anchor),
            InflateViewProvider(R.layout.coach_sample),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                marginVertical = 16.dp
            )
        )
    }

    private fun Coach.Creator.createFrameScene(): CoachScene {
        return CoachScene(
            "frame",
            RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            IdViewFinder(R.id.frame),
            InflateViewProvider(R.layout.coach_sample),
            FrameCoachSceneLayoutProvider(
                gravity = Gravity.CENTER
            )
        )
    }

    private fun showFirstCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createAnchorScene())
            addScene(createFrameScene())
        }.scheduleShow()
    }

    private fun showAnchorCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createAnchorScene())
        }.show()
    }

    private fun showFrameCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createFrameScene())
        }.show()
    }
}