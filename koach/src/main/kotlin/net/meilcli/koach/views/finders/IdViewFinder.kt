package net.meilcli.koach.views.finders

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import net.meilcli.koach.IViewFinder

class IdViewFinder(
    @IdRes private val id: Int
) : IViewFinder {

    override fun find(parent: ViewGroup): View? {
        return parent.findViewById(id)
    }
}