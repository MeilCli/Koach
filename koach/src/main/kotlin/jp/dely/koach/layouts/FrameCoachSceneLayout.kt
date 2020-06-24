package jp.dely.koach.layouts

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.Px
import jp.dely.koach.ICoachSceneLayout

@SuppressLint("ViewConstructor")
class FrameCoachSceneLayout(
    context: Context,
    private val gravity: Int,
    @Px private val marginTop: Int,
    @Px private val marginBottom: Int,
    @Px private val marginLeft: Int,
    @Px private val marginRight: Int
) : FrameLayout(context), ICoachSceneLayout {

    init {
        fitsSystemWindows = true
    }

    override var coachView: View? = null
        private set

    override fun addCoachView(view: View) {
        val layoutParams = LayoutParams(
            view.layoutParams ?: LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
        ).apply {
            gravity = this@FrameCoachSceneLayout.gravity
            topMargin = marginTop
            bottomMargin = marginBottom
            leftMargin = marginLeft
            rightMargin = marginRight
        }
        addView(view, layoutParams)
        coachView = view
    }
}