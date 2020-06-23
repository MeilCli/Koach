package jp.dely.koach.app

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_anchor.*
import jp.dely.koach.Coach
import jp.dely.koach.CoachOverlay
import jp.dely.koach.CoachScene
import jp.dely.koach.layouts.providers.AnchorCoachSceneLayoutProvider
import jp.dely.koach.overlays.shapes.RectOverlayShape
import jp.dely.koach.views.finders.IdViewFinder
import jp.dely.koach.views.providers.TextViewProvider

@SuppressLint("RtlHardcoded")
class AnchorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anchor)

        leftTop.setOnClickListener {
            showLeftTopCoach()
        }
        rightTop.setOnClickListener {
            showRightTopCoach()
        }
        center.setOnClickListener {
            showCenterCoach()
        }
        leftBottom.setOnClickListener {
            showLeftBottomCoach()
        }
        rightBottom.setOnClickListener {
            showRightBottomCoach()
        }

        showFirstCoach()
    }

    private fun showFirstCoach() {
        val coach = Coach.create(this, CoachOverlay()) {
            addScene(
                CoachScene(
                    "left top",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.leftTop),
                    TextViewProvider(
                        text = "Left Top Button anchored",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.BOTTOM or Gravity.LEFT,
                        gravity = Gravity.BOTTOM or Gravity.RIGHT,
                        marginVertical = 16.dp
                    )
                )
            )
            addScene(
                CoachScene(
                    "right top",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.rightTop),
                    TextViewProvider(
                        text = "Right Top Button anchored",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1,
                        gravity = Gravity.RIGHT
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.BOTTOM or Gravity.RIGHT,
                        gravity = Gravity.BOTTOM or Gravity.LEFT,
                        marginVertical = 16.dp
                    )
                )
            )
            addScene(
                CoachScene(
                    "center",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.center),
                    TextViewProvider(
                        text = "Center Button anchored",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1,
                        gravity = Gravity.CENTER
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.CENTER,
                        gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        marginVertical = 48.dp
                    )
                )
            )
            addScene(
                CoachScene(
                    "left bottom",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.leftBottom),
                    TextViewProvider(
                        text = "Left Bottom Button anchored",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.TOP or Gravity.LEFT,
                        gravity = Gravity.TOP or Gravity.RIGHT,
                        marginVertical = 16.dp
                    )
                )
            )
            addScene(
                CoachScene(
                    "right bottom",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.rightBottom),
                    TextViewProvider(
                        text = "Right Bottom Button anchored",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1,
                        gravity = Gravity.RIGHT
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.TOP or Gravity.RIGHT,
                        gravity = Gravity.TOP or Gravity.LEFT,
                        marginVertical = 16.dp
                    )
                )
            )
        }
        coach.scheduleShow()
    }

    private fun showLeftTopCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(
                CoachScene(
                    "left top layout",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.leftTop),
                    TextViewProvider(
                        text = "The starting point is set to the bottom left of the button, and it is placed from the starting point to the bottom right.",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.BOTTOM or Gravity.LEFT,
                        gravity = Gravity.BOTTOM or Gravity.RIGHT,
                        marginVertical = 16.dp
                    )
                )
            )
        }.show()
    }

    private fun showRightTopCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(
                CoachScene(
                    "right top layout",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.rightTop),
                    TextViewProvider(
                        text = "The starting point is set to the bottom right of the button, and it is placed from the starting point to the bottom left.",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1,
                        gravity = Gravity.RIGHT
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.BOTTOM or Gravity.RIGHT,
                        gravity = Gravity.BOTTOM or Gravity.LEFT,
                        marginVertical = 16.dp
                    )
                )
            )
        }.show()
    }

    private fun showCenterCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(
                CoachScene(
                    "center layout",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.center),
                    TextViewProvider(
                        text = "The starting point is set to the center of the button, and it is placed from the starting point to the top center-horizontal.",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1,
                        gravity = Gravity.CENTER
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.CENTER,
                        gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        marginVertical = 48.dp
                    )
                )
            )
        }.show()
    }

    private fun showLeftBottomCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(
                CoachScene(
                    "left bottom layout",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.leftBottom),
                    TextViewProvider(
                        text = "The starting point is set to the top left of the button, and it is placed from the starting point to the top right.",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.TOP or Gravity.LEFT,
                        gravity = Gravity.TOP or Gravity.RIGHT,
                        marginVertical = 16.dp
                    )
                )
            )
        }.show()
    }

    private fun showRightBottomCoach() {
        Coach.create(this, CoachOverlay()) {
            addScene(
                CoachScene(
                    "right bottom layout",
                    RectOverlayShape(
                        margin = 8.dp,
                        radius = 4.dp
                    ),
                    IdViewFinder(R.id.rightBottom),
                    TextViewProvider(
                        text = "The starting point is set to the top right of the button, and it is placed from the starting point to the top left.",
                        textColor = Color.WHITE,
                        textAppearance = R.style.TextAppearance_AppCompat_Body1,
                        gravity = Gravity.RIGHT
                    ),
                    AnchorCoachSceneLayoutProvider(
                        anchorGravity = Gravity.TOP or Gravity.RIGHT,
                        gravity = Gravity.TOP or Gravity.LEFT,
                        marginVertical = 16.dp
                    )
                )
            )
        }.show()
    }
}