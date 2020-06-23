package jp.dely.koach

import android.content.Context
import android.view.View
import jp.dely.koach.ICoachSceneLayout

interface IViewProvider {

    fun provide(context: Context, layout: ICoachSceneLayout): View
}