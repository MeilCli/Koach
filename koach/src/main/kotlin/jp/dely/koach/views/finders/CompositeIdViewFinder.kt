package jp.dely.koach.views.finders

import android.graphics.Rect
import android.view.ViewGroup
import androidx.annotation.IdRes
import jp.dely.koach.IViewFinder
import jp.dely.koach.ViewSpec

class CompositeIdViewFinder(
    @IdRes
    private vararg val ids: Int
) : IViewFinder {

    override fun find(parent: ViewGroup): ViewSpec? {
        if (ids.isEmpty()) {
            return null
        }

        val viewSpecs = ids.map { IdViewFinder(it).find(parent) }
        if (viewSpecs.any { it == null }) {
            return null
        }

        val nonNullViewSpecs = viewSpecs.filterNotNull()

        val rect = Rect(
            checkNotNull(nonNullViewSpecs.map { it.rect.left }.min()),
            checkNotNull(nonNullViewSpecs.map { it.rect.top }.min()),
            checkNotNull(nonNullViewSpecs.map { it.rect.right }.max()),
            checkNotNull(nonNullViewSpecs.map { it.rect.bottom }.max())
        )
        val invokeClick: (Float, Float) -> Unit = { x, y ->
            nonNullViewSpecs.forEach {
                if (it.rect.contains(x.toInt(), y.toInt())) {
                    it.invokeClick(x, y)
                }
            }
        }
        return ViewSpec(rect, invokeClick)
    }
}