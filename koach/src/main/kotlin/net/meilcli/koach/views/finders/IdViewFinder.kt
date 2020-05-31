package net.meilcli.koach.views.finders

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import net.meilcli.koach.IViewFinder
import net.meilcli.koach.ViewSpec

class IdViewFinder(
    @IdRes private val id: Int
) : IViewFinder {

    override fun find(parent: ViewGroup): ViewSpec? {
        val targetView = parent.findViewById<View>(id) ?: return null
        return ViewSpec.getSpec(parent, targetView)
    }
}