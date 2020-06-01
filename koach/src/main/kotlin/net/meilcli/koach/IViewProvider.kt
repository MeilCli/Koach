package net.meilcli.koach

import android.content.Context
import android.view.View

interface IViewProvider {

    fun provide(context: Context, layout: ICoachSceneLayout): View
}