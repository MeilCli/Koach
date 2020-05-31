package net.meilcli.koach

import android.graphics.Rect
import android.view.View

data class ViewSpec(
    val rect: Rect
) {
    companion object {

        val empty = ViewSpec(Rect(0, 0, 0, 0))

        fun getSpec(view: View): ViewSpec {
            val location = IntArray(2)
            view.getLocationInWindow(location)
            val left = location[0]
            val top = location[1]
            val width = view.width
            val height = view.height

            val rect = Rect(left, top, left + width, top + height)

            return ViewSpec(rect)
        }
    }
}