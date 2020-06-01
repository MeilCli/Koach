package net.meilcli.koach.views.providers

import android.content.Context
import android.view.View
import net.meilcli.koach.ICoachSceneLayout
import net.meilcli.koach.IViewProvider

class ViewProvider(private val view: View) : IViewProvider {

    override fun provide(context: Context, layout: ICoachSceneLayout): View {
        return view
    }
}