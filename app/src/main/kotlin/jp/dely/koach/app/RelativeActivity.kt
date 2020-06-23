package jp.dely.koach.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.dely.koach.Coach
import jp.dely.koach.CoachOverlay
import jp.dely.koach.CoachScene
import jp.dely.koach.layouts.RelativeDirection
import jp.dely.koach.layouts.providers.RelativeCoachSceneLayoutProvider
import jp.dely.koach.overlays.shapes.RectOverlayShape
import jp.dely.koach.views.finders.IdViewFinder
import jp.dely.koach.views.providers.InflateViewProvider
import kotlinx.android.synthetic.main.activity_relative.*

class RelativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)

        left.setOnClickListener {
            showLeftCoach()
        }
        right.setOnClickListener {
            showRightCoach()
        }
        below.setOnClickListener {
            showBelowCoach()
        }
        above.setOnClickListener {
            showAboveCoach()
        }
        showFirstCoach()
    }

    private fun Coach.Creator.createLeftScene(): CoachScene {
        return CoachScene(
            "left",
            RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            IdViewFinder(R.id.left),
            InflateViewProvider(R.layout.coach_sample_vertical),
            RelativeCoachSceneLayoutProvider(
                RelativeDirection.Left,
                margin = 24.dp
            )
        )
    }

    private fun Coach.Creator.createRightScene(): CoachScene {
        return CoachScene(
            "right",
            RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            IdViewFinder(R.id.right),
            InflateViewProvider(R.layout.coach_sample_vertical),
            RelativeCoachSceneLayoutProvider(
                RelativeDirection.Right,
                margin = 24.dp
            )
        )
    }

    private fun Coach.Creator.createBelowScene(): CoachScene {
        return CoachScene(
            "below",
            RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            IdViewFinder(R.id.below),
            InflateViewProvider(R.layout.coach_sample_horizontal),
            RelativeCoachSceneLayoutProvider(
                RelativeDirection.Below,
                margin = 24.dp
            )
        )
    }

    private fun Coach.Creator.createAboveScene(): CoachScene {
        return CoachScene(
            "above",
            RectOverlayShape(
                margin = 8.dp,
                radius = 8.dp
            ),
            IdViewFinder(R.id.above),
            InflateViewProvider(R.layout.coach_sample_horizontal),
            RelativeCoachSceneLayoutProvider(
                RelativeDirection.Above,
                margin = 24.dp
            )
        )
    }

    private fun showFirstCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createLeftScene())
            addScene(createRightScene())
            addScene(createBelowScene())
            addScene(createAboveScene())
        }.scheduleShow()
    }

    private fun showLeftCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createLeftScene())
        }.show()
    }

    private fun showRightCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createRightScene())
        }.show()
    }

    private fun showBelowCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createBelowScene())
        }.show()
    }

    private fun showAboveCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(createAboveScene())
        }.show()
    }
}