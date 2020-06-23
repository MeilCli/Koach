package jp.dely.koach

import android.view.ViewGroup

interface IViewFinder {

    fun find(parent: ViewGroup): ViewSpec?
}