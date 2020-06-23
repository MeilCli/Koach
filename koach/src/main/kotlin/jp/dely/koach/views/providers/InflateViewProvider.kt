package jp.dely.koach.views.providers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import jp.dely.koach.ICoachSceneLayout
import jp.dely.koach.IViewProvider

class InflateViewProvider(@LayoutRes private val layoutRes: Int) :
    IViewProvider {

    override fun provide(context: Context, layout: ICoachSceneLayout): View {
        return if (layout is ViewGroup) {
            LayoutInflater.from(context).inflate(layoutRes, layout, false)
        } else {
            LayoutInflater.from(context).inflate(layoutRes, null)
        }
    }
}