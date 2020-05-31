package net.meilcli.koach

import android.graphics.Point
import android.graphics.Rect
import android.view.View

data class TargetViewSpec(
    val rect: Rect
) {
    companion object {

        val empty = TargetViewSpec(Rect(0, 0, 0, 0))

        fun getSpec(view: View): TargetViewSpec {
            val location = IntArray(2)
            view.getLocationInWindow(location)
            val left = location[0]
            val top = location[1]
            val width = view.width
            val height = view.height

            val rect = Rect(left, top, left + width, top + height)

            return TargetViewSpec(rect)
        }
    }
}