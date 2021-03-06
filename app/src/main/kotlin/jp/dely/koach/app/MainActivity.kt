package jp.dely.koach.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anchor.setOnClickListener {
            startActivity(Intent(this, AnchorActivity::class.java))
        }
        relative.setOnClickListener {
            startActivity(Intent(this, RelativeActivity::class.java))
        }
        animation.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
        shape.setOnClickListener {
            startActivity(Intent(this, ShapeActivity::class.java))
        }
        animationShape.setOnClickListener {
            startActivity(Intent(this, AnimationShapeActivity::class.java))
        }
        animationScene.setOnClickListener {
            startActivity(Intent(this, AnimationSceneActivity::class.java))
        }
        clickTarget.setOnClickListener {
            startActivity(Intent(this, ClickTargetActivity::class.java))
        }
        viewGroup.setOnClickListener {
            startActivity(Intent(this, ViewGroupActivity::class.java))
        }
        coachView.setOnClickListener {
            startActivity(Intent(this, CoachViewActivity::class.java))
        }
    }
}
