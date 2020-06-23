package jp.dely.koach.views.providers

import android.content.Context
import android.view.View
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.IViewProvider

class ViewProvider(private val view: View) : IViewProvider {

    override fun provide(context: Context, layout: ICoachSceneLayout): View {
        return view
    }
}