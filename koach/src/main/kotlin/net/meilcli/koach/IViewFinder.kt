package net.meilcli.koach

import android.view.View
import android.view.ViewGroup

interface IViewFinder {

    fun find(parent: ViewGroup): View?
}