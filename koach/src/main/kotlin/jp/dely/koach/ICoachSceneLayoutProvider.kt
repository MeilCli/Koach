package jp.dely.koach

import android.content.Context

interface ICoachSceneLayoutProvider {

    fun provide(context: Context, targetViewSpec: ViewSpec): ICoachSceneLayout
}