package net.meilcli.koach.views.providers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import net.meilcli.koach.IViewProvider

class InflateViewProvider(@LayoutRes private val layout: Int) : IViewProvider {

    override fun provide(context: Context): View {
        return LayoutInflater.from(context).inflate(layout, null)
    }
}