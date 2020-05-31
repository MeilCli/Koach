package net.meilcli.koach.layouts

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.Px
import net.meilcli.koach.ICoachSceneLayout

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

    override fun addCoachView(view: View) {
        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity
        ).apply {
            topMargin = marginTop
            bottomMargin = marginBottom
            leftMargin = marginLeft
            rightMargin = marginRight
        }
        addView(view, layoutParams)
    }
}