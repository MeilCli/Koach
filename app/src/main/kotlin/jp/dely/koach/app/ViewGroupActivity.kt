package jp.dely.koach.app

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_group.*
import jp.dely.koach.Coach
import jp.dely.koach.CoachOverlay
import jp.dely.koach.CoachScene
import jp.dely.koach.layouts.providers.AnchorCoachSceneLayoutProvider
import jp.dely.koach.overlays.shapes.RectOverlayShape
import jp.dely.koach.views.finders.IdViewFinder
import jp.dely.koach.views.providers.TextViewProvider

class ViewGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_group)

        showToast.setOnClickListener {
            Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show()
        }
        showCoach.setOnClickListener {
            showCoach(false)
        }

        showCoach(true)
    }

    private fun showCoach(schedule: Boolean) {
        val coach = Coach.create(
            container,
            CoachOverlay(
                canClickTargetView = true
            )
        ) {
            addScene(
                CoachScene(
                    "show toast",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 8.dp
                    ),
                    IdViewFinder(R.id.showToast),
                    TextViewProvider(
                        text = "Show Toast!",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.CENTER,
                        gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL,
                        marginVertical = 32.dp
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