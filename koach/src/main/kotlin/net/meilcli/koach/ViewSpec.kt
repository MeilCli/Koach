package net.meilcli.koach

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup

/**
 * [invokeClick]'s first argument is touchX in parent layout
 * [invokeClick]'s second argument is touchY in parent layout
 */
data class ViewSpec(
    val rect: Rect,
    val invokeClick: (Float, Float) -> Unit
) {
    companion object {

        val empty = ViewSpec(Rect(0, 0, 0, 0)) { _, _ -> }

        fun getSpec(parent: ViewGroup, view: View): ViewSpec {
            val location = IntArray(2)

            parent.getLocationInWindow(location)
            val parentLeft = location[0]
            val parentTop = location[1]

            view.getLocationInWindow(location)
            val left = location[0] - parentLeft
            val top = location[1] - parentTop
            val width = view.width
            val height = view.height

            val rect = Rect(left, top, left + width, top + height)

            return ViewSpec(rect) { _, _ -> view.performClick() }
        }
    }
}