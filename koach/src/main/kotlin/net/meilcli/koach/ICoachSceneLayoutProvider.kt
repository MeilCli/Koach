package net.meilcli.koach

import android.content.Context

interface ICoachSceneLayoutProvider {

    fun provide(context: Context, targetViewSpec: TargetViewSpec): ICoachSceneLayout
}